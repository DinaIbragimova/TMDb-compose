package com.ibragimova.featuremovies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ibragimova.basesources.NotificationsSource
import com.ibragimova.basetmdb.data.TmdbRepository
import com.ibragimova.basetmdb.domain.MovieModel
import com.ibragimova.basesources.Action
import com.ibragimova.coreui.viewmodel.SimpleViewModel
import com.ibragimova.coreui.viewmodel.publish
import com.ibragimova.featuremovies.presentation.screen.MovieTileState
import com.ibragimova.featuremovies.presentation.toMovieTileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
    private val action: Action,
    private val notificationsSource: NotificationsSource,
) : SimpleViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()


    init {
        subscribeToPopularMovies()
        subscribeToNowPlayingMovies()
        subscribeToTopRatedMovies()
        subscribeToUpComingMovies()

        onNowPlayingMoviesReloadClicked()
        onPopularMoviesReloadClicked()
        onTopRatedMoviesReloadClicked()
        onUpComingMoviesReloadClicked()
    }

    private fun subscribeToPopularMovies() {
        tmdbRepository.popularMoviesState.onEach { state ->
            _uiState.update {
                it.copy(
                    popularMovies = state.toMovieTileState(
                        onItemClicked = ::onItemClicked,
                        onReloadClicked = ::onPopularMoviesReloadClicked,
                    ),
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun subscribeToNowPlayingMovies() {
        tmdbRepository.nowPlayingMoviesState.onEach { state ->
            _uiState.update {
                it.copy(
                    nowPlayingMovies = state.toMovieTileState(
                        onItemClicked = ::onItemClicked,
                        onReloadClicked = ::onNowPlayingMoviesReloadClicked,
                    ),
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun subscribeToTopRatedMovies() {
        tmdbRepository.topRatedMoviesState.onEach { state ->
            _uiState.update {
                it.copy(
                    topRatedMovies = state.toMovieTileState(
                        onItemClicked = ::onItemClicked,
                        onReloadClicked = ::onTopRatedMoviesReloadClicked,
                    ),
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun subscribeToUpComingMovies() {
        tmdbRepository.upcomingMoviesState.onEach { state ->
            _uiState.update {
                it.copy(
                    upComingMovies = state.toMovieTileState(
                        onItemClicked = ::onItemClicked,
                        onReloadClicked = ::onUpComingMoviesReloadClicked,
                    ),
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun onItemClicked(movie: MovieModel) {
        viewModelScope.launch {
            _command publish Command.OpenMovieDetailsScreen(movie.id)
        }
    }

    private fun onPopularMoviesReloadClicked() {
        viewModelScope.launch {
            action.execute {
                tmdbRepository.updatePopularMovies()
            }
        }
    }

    private fun onNowPlayingMoviesReloadClicked() {
        viewModelScope.launch {
            action.execute {
                tmdbRepository.updateNowPlayingMovies()
            }
        }
    }

    private fun onTopRatedMoviesReloadClicked() {
        viewModelScope.launch {
            action.execute {
                tmdbRepository.updateTopRatedMovies()
            }
        }
    }

    private fun onUpComingMoviesReloadClicked() {
        viewModelScope.launch {
            action.execute {
                tmdbRepository.updateUpcomingMovies()
            }
        }
    }

    data class UiState(
        val popularMovies: List<MovieTileState> = List(6) { MovieTileState.Shimmer },
        val nowPlayingMovies: List<MovieTileState> = List(6) { MovieTileState.Shimmer },
        val topRatedMovies: List<MovieTileState> = List(6) { MovieTileState.Shimmer },
        val upComingMovies: List<MovieTileState> = List(6) { MovieTileState.Shimmer },
    )

    sealed class Command {
        data class OpenMovieDetailsScreen(val id: Int) : Command()
    }
}