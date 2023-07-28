package com.ibragimova.basetmdb.di

import com.ibragimova.basetmdb.data.TmdbApi
import com.ibragimova.basetmdb.data.TmdbRepository
import com.ibragimova.basetmdb.data.TmdbRepositoryImpl
import com.ibragimova.coredata.network.ApiConfig
import com.ibragimova.coredata.network.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun notificationsApi(config: ApiConfig) = config
        .serviceBuilder(TmdbApi::class.java)
        .service(ApiService.General)
        .interceptor(config.authenticationInterceptor)
        .interceptor(config.commonHeaderInterceptor)
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
interface DataBindModule {

    @Binds
    @Singleton
    fun tmdbRepository(repository: TmdbRepositoryImpl): TmdbRepository
}