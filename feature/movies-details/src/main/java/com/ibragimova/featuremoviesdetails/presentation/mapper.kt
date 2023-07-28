package com.ibragimova.featuremoviesdetails.presentation

import com.ibragimova.basetmdb.domain.MovieDetailsModel
import com.ibragimova.corecommon.R
import com.ibragimova.corecommon.container.imageValue
import com.ibragimova.featuremoviesdetails.presentation.screen.MovieDetailsTileState

fun MovieDetailsModel.toMovieDetailsTileState() = MovieDetailsTileState.Data(
    name = originalTitle,
    backdropImage = backdropImage ?: R.drawable.ic_camera.imageValue(),
    posterImage = posterImage ?: R.drawable.ic_camera.imageValue(),
    releaseDate = releaseDate,
    genre = genres.joinToString { it.name },
    overview = overview,
)
