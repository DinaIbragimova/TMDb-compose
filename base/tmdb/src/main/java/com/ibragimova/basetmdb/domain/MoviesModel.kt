package com.ibragimova.basetmdb.domain

import com.ibragimova.corecommon.container.ImageValue

data class MoviesModel(
    val movies : List<MovieModel>,
)

data class MovieModel(
    val backdropImage: ImageValue?,
    val id: Int,
    val originalTitle: String,
    val posterImage: ImageValue?,
    val title: String,
    val name: String,
    val type: String,
)