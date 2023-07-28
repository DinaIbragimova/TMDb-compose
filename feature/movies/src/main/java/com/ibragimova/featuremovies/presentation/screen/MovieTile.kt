package com.ibragimova.featuremovies.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ibragimova.corecommon.container.ImageValue
import com.ibragimova.coreuicompose.tools.TileState
import com.ibragimova.coreuicompose.tools.doOnClick
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.uikit.listtile.MessageBannerState
import com.ibragimova.coreuicompose.uikit.other.ShimmerTile
import com.ibragimova.coreuicompose.uikit.other.ShimmerTileLine
import com.ibragimova.coreuitheme.compose.AppTheme

sealed class MovieTileState : TileState {

    data class Data(
        val name: String,
        val image: ImageValue,
        val onItemClicked: () -> Unit,
    ) : MovieTileState()


    object Shimmer : MovieTileState()

    data class Error(val onClick: () -> Unit) : MovieTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        MovieTile(modifier = modifier, data = this)
    }
}

@Composable
private fun MovieTile(
    modifier: Modifier,
    data: MovieTileState,
) {
    when (data) {
        is MovieTileState.Data -> Data(modifier, data)
        is MovieTileState.Shimmer -> Shimmer(modifier = modifier)
        is MovieTileState.Error -> MessageBannerState
            .defaultState(onClick = data.onClick)
            .Content(modifier = modifier)
    }
}

@Composable
private fun Data(
    modifier: Modifier,
    data: MovieTileState.Data,
) {
    Container(
        modifier = modifier,
        clickListener = data.onItemClicked
    ) {
        Image(
            painter = data.image.get(),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(130.dp)
                .clip(shape = AppTheme.shapes.small),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = data.name,
            style = AppTheme.specificTypography.bodyMedium,
            color = AppTheme.specificColorScheme.textPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun Shimmer(
    modifier: Modifier,
) {
    Container(modifier) {
        ShimmerTile(
            modifier = Modifier
                .width(100.dp)
                .height(130.dp),
            shape = AppTheme.shapes.small,
        )
        Spacer(modifier = Modifier.height(4.dp))
        ShimmerTileLine(width = 100.dp)
    }
}

@Composable
private fun Container(
    modifier: Modifier = Modifier,
    clickListener: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .shadow(elevation = 16.dp, shape = AppTheme.shapes.small)
            .background(
                color = AppTheme.specificColorScheme.white,
                shape = AppTheme.shapes.small,
            )
            .doOnClick(onClick = clickListener)
            .padding(12.dp),
        content = content,
    )
}