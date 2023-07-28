package com.ibragimova.featuremoviesdetails.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.ibragimova.coreuicompose.tools.inset
import com.ibragimova.coreuicompose.tools.insetAllExcludeTop
import com.ibragimova.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.ibragimova.coreuitheme.compose.AppTheme
import com.ibragimova.featuremoviesdetails.ScreenNavigator
import com.ibragimova.featuremoviesdetails.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val viewModel = hiltViewModel<MovieDetailsViewModel>()

    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsScreen(
        uiState = uiState,
        onBackClicked = router::back,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsScreen(
    uiState: MovieDetailsViewModel.UiState,
    onBackClicked: () -> Boolean,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = null,
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
            uiState.movieDetails.Content(modifier = Modifier)
        }
    }
}