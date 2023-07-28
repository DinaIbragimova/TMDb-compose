package com.ibragimova.tmdb

import com.ibragimova.corenavigation.MovieDetailsFeatureProvider
import com.ibragimova.corenavigation.MoviesFeatureProvider
import com.ibragimova.featuremovies.MoviesFeatureProviderImpl
import com.ibragimova.featuremoviesdetails.MovieDetailsFeatureProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FeatureProviderModule {

    @Binds
    @Singleton
    fun movieDetailsProvider(provider: MovieDetailsFeatureProviderImpl): MovieDetailsFeatureProvider

    @Binds
    @Singleton
    fun moviesProvider(provider: MoviesFeatureProviderImpl): MoviesFeatureProvider
}