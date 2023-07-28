package com.ibragimova.featuremovies.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibragimova.corecommon.container.textValue
import com.ibragimova.corecommon.strings.StringKey
import com.ibragimova.coreui.viewmodel.collectAsCommand
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.tools.inset
import com.ibragimova.coreuicompose.tools.insetAllExcludeTop
import com.ibragimova.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.ibragimova.coreuitheme.compose.AppTheme
import com.ibragimova.featuremovies.ScreenNavigator
import com.ibragimova.featuremovies.presentation.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val viewModel = hiltViewModel<MoviesViewModel>()

    val uiState by viewModel.uiState.collectAsState()

    viewModel.command.collectAsCommand {
        when (it) {
            is MoviesViewModel.Command.OpenMovieDetailsScreen -> router.toMoviesDetailsScreen(it.id)
        }
    }

    MoviesScreen(
        uiState = uiState,
        onBackClicked = router::back,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesScreen(
    uiState: MoviesViewModel.UiState,
    onBackClicked: () -> Boolean,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = StringKey.MoviesTitle.textValue(),
                scrollBehavior = scrollBehavior,
                navigationIcon = AppTheme.specificIcons.back to onBackClicked,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .inset(insetAllExcludeTop())
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
        ) {
            MoviesBlock(
                title = StringKey.MoviesTitlePlayingInTheatres.textValue().get().text,
                movies = uiState.nowPlayingMovies,
            )
            MoviesBlock(
                title = StringKey.MoviesTitlePopular.textValue().get().text,
                movies = uiState.popularMovies,
            )
            MoviesBlock(
                title = StringKey.MoviesTitleUpComing.textValue().get().text,
                movies = uiState.upComingMovies,
            )
            MoviesBlock(
                title = StringKey.MoviesTitleTopRated.textValue().get().text,
                movies = uiState.topRatedMovies,
            )
        }
    }
}

@Composable
private fun MoviesBlock(
    title: String,
    movies: List<MovieTileState>,
) {
    Text(
        text = title,
        style = AppTheme.specificTypography.bodyLarge,
    )
    LazyRow(
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        items(items = movies) {
            it.Content(modifier = Modifier)
        }
    }

}