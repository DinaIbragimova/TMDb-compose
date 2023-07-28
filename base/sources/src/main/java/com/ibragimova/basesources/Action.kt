package com.ibragimova.basesources

import com.ibragimova.corecommon.ext.log
import com.ibragimova.corecommon.model.AppError
import com.ibragimova.corecommon.model.Effect
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

interface Action {

    suspend fun <T : Any> execute(block: suspend CoroutineScope.() -> Effect<T>): Effect<T>
}

class ActionImpl @Inject constructor(
    private val notificationsSource: NotificationsSource,
) : Action {

    override suspend fun <T : Any> execute(block: suspend CoroutineScope.() -> Effect<T>): Effect<T> {
        val effect = try {
            coroutineScope {
                var effect = block()
                if (effect.exceptionOrNull?.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    // todo: refresh
                    effect = block()
                }
                effect
            }
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> throw e
                else -> Effect.error(AppError.Unknown(cause = e))
            }
        }

        return effect.also {
            it.exceptionOrNull?.let { error -> handle(error) }
        }
    }

    private suspend fun handle(error: AppError) {
        log(error.stackTraceToString())

        when (error) {
            is AppError.Unknown -> notificationsSource.sendError(error)
            is AppError.Custom -> when (error.code) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    // todo: logout
                    notificationsSource.sendError(error)
                }
                else -> {}
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface ActionModule {

    @Binds
    @Singleton
    fun action(action: ActionImpl): Action
}