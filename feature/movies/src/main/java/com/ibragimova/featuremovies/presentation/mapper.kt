package com.ibragimova.featuremovies.presentation

import com.ibragimova.basetmdb.domain.MovieModel
import com.ibragimova.corecommon.model.Effect
import com.ibragimova.corecommon.model.Loading
import com.ibragimova.corecommon.model.State
import com.ibragimova.corecommon.R
import com.ibragimova.corecommon.container.imageValue
import com.ibragimova.featuremovies.presentation.screen.MovieTileState

fun State<List<MovieModel>>.toMovieTileState(
    onItemClicked: (MovieModel) -> Unit,
    onReloadClicked: () -> Unit,
) = when (this) {
    is Loading -> List(6) { MovieTileState.Shimmer }
    is Effect -> when {
        isSuccess -> {
            requireData.map {
                it.toMovieTileState(
                    onItemClicked = onItemClicked,
                )
            }
        }
        else -> {
            listOf(MovieTileState.Error(onClick = onReloadClicked))
        }
    }
}

fun MovieModel.toMovieTileState(
    onItemClicked: (MovieModel) -> Unit,
) = MovieTileState.Data(
    name = name,
    image = posterImage ?: R.drawable.ic_camera.imageValue(),
    onItemClicked = { onItemClicked(this) }
)
