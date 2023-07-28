package com.ibragimova.tmdb

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.ibragimova.coredata.coroutine.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import coil.decode.SvgDecoder
import com.ibragimova.coredata.network.ApiConfig

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder, ImageLoaderFactory {

    @Inject lateinit var apiConfig: ApiConfig

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun newImageLoader() = ImageLoader.Builder(this)
        .okHttpClient {
            apiConfig.okHttpBuilder()
                .build()
        }
        .components {
            add(SvgDecoder.Factory())
        }
        .crossfade(true)
        .build()
}