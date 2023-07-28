package com.ibragimova.basetmdb.data

import com.ibragimova.basetmdb.data.model.DetailsResponseDto
import com.ibragimova.basetmdb.data.model.MovieDto
import com.ibragimova.basetmdb.data.model.MovieResponseDto
import com.ibragimova.basetmdb.domain.GenreModel
import com.ibragimova.basetmdb.domain.MovieDetailsModel
import com.ibragimova.basetmdb.domain.MovieModel
import com.ibragimova.basetmdb.domain.MoviesModel
import com.ibragimova.corecommon.container.imageValue
import com.ibragimova.corecommon.date.toOffsetLocalDateTime
import java.time.ZonedDateTime

fun DetailsResponseDto.toMovieDetailsModel() = MovieDetailsModel(
    backdropImage = backdropPath?.imageValue(),
    genres = genres.map { GenreModel(it.name) },
    id = id,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterImage = posterPath?.imageValue(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    title = title,
    firstAirDate = firstAirDate,
)

fun MovieResponseDto.toMoviesModel() = MoviesModel(
    movies = movies.map { it.toMovieModel() },
)

private fun MovieDto.toMovieModel() = MovieModel(
    backdropImage = backdropPath?.imageValue(),
    id = id,
    originalTitle = originalTitle,
    posterImage = posterPath?.imageValue(),
    title = title,
    name = name,
    type = type,
)