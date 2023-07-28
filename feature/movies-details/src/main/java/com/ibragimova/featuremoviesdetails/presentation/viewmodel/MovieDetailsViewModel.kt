package com.ibragimova.featuremoviesdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ibragimova.basetmdb.data.TmdbRepository
import com.ibragimova.basesources.Action
import com.ibragimova.corenavigation.AppRoute
import com.ibragimova.corenavigation.base.requireArgs
import com.ibragimova.coreui.viewmodel.SimpleViewModel
import com.ibragimova.featuremoviesdetails.presentation.screen.MovieDetailsTileState
import com.ibragimova.featuremoviesdetails.presentation.toMovieDetailsTileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tmdbRepository: TmdbRepository,
    private val action: Action,
) : SimpleViewModel() {

    private val args = savedStateHandle.requireArgs<AppRoute.MoviesDetails.Args>()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            action.execute {
                tmdbRepository.getMovieDetails(args.id)
            }.doOnSuccess { movie ->
                _uiState.update {
                    it.copy(movieDetails = movie.toMovieDetailsTileState())
                }
            }.doOnError { _, _ ->
                _uiState.update {
                    it.copy(movieDetails = MovieDetailsTileState.Error(onClick = ::onReloadClicked))
                }
            }
        }
    }

    private fun onReloadClicked() {
        loadMovieDetails()
    }

    data class UiState(
        val movieDetails: MovieDetailsTileState = MovieDetailsTileState.Shimmer,
    )
}