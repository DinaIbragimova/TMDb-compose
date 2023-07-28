package com.ibragimova.basetmdb.data

import com.ibragimova.basetmdb.data.model.DetailsResponseDto
import com.ibragimova.basetmdb.data.model.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("region") region: String
    ): Response<MovieResponseDto>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("region") region: String
    ): Response<MovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("region") region: String
    ): Response<MovieResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") movieId: Int,
    ): Response<DetailsResponseDto>
}