package com.ibragimova.basetmdb.domain

import com.ibragimova.corecommon.container.ImageValue
import com.ibragimova.corecommon.model.DateTime

data class MovieDetailsModel(
    val backdropImage: ImageValue?,
    val genres: List<GenreModel>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterImage: ImageValue?,
    val releaseDate: DateTime,
    val revenue: Int,
    val runtime: Int,
    val title: String,
    val firstAirDate: DateTime,
)

data class GenreModel(
    val name: String,
)