package com.ibragimova.coredata.network

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ibragimova.coredata.database.TokenStorage
import com.ibragimova.coredata.network.interceptors.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun authenticationInterceptor() = AuthenticationInterceptor()

    @Provides
    @Singleton
    fun chuckInterceptor(application: Application) = ChuckerInterceptor.Builder(application).build()
}