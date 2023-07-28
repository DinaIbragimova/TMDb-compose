package com.ibragimova.featuremoviesdetails.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

sealed class MovieDetailsTileState : TileState {

    data class Data(
        val name: String,
        val backdropImage: ImageValue,
        val posterImage: ImageValue,
        val releaseDate: String,
        val genre: String,
        val overview: String,

    ) : MovieDetailsTileState()


    object Shimmer : MovieDetailsTileState()

    data class Error(val onClick: () -> Unit) : MovieDetailsTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        MovieTile(modifier = modifier, data = this)
    }
}

@Composable
private fun MovieTile(
    modifier: Modifier,
    data: MovieDetailsTileState,
) {
    when (data) {
        is MovieDetailsTileState.Data -> Data(modifier, data)
        is MovieDetailsTileState.Shimmer -> Shimmer(modifier = modifier)
        is MovieDetailsTileState.Error -> MessageBannerState
            .defaultState(onClick = data.onClick)
            .Content(modifier = modifier)
    }
}

@Composable
private fun Data(
    modifier: Modifier,
    data: MovieDetailsTileState.Data,
) {
    Container(
        modifier = modifier,
    ) {
        Box {
            Image(
                painter = data.backdropImage.get(),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.FillWidth,
            )
            Image(
                painter = data.backdropImage.get(),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
                    .width(100.dp)
                    .height(130.dp)
                    .clip(shape = AppTheme.shapes.small),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = data.name,
            style = AppTheme.specificTypography.bodyLarge,
            color = AppTheme.specificColorScheme.textPrimary,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = data.releaseDate,
            style = AppTheme.specificTypography.bodyMedium,
            color = AppTheme.specificColorScheme.textSecondary,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.genre,
            style = AppTheme.specificTypography.bodyMedium,
            color = AppTheme.specificColorScheme.textSecondary,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.overview,
            style = AppTheme.specificTypography.bodyMedium,
            color = AppTheme.specificColorScheme.textSecondary,
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
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(12.dp),
        content = content,
    )
}