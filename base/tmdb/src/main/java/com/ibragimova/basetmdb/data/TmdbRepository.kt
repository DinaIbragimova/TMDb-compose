package com.ibragimova.basetmdb.data

import com.ibragimova.basetmdb.domain.MovieDetailsModel
import com.ibragimova.basetmdb.domain.MovieModel
import com.ibragimova.basetmdb.domain.MoviesModel
import com.ibragimova.corecommon.model.Completable
import com.ibragimova.corecommon.model.Effect
import com.ibragimova.corecommon.model.Loading
import com.ibragimova.corecommon.model.State
import com.ibragimova.corecommon.strings.DEFAULT_LOCALE
import com.ibragimova.coredata.coroutine.IoDispatcher
import com.ibragimova.coredata.network.apiCall
import com.ibragimova.coreui.viewmodel.tryPublish
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface TmdbRepository {

    val popularMoviesState: StateFlow<State<List<MovieModel>>>

    val upcomingMoviesState: StateFlow<State<List<MovieModel>>>

    val nowPlayingMoviesState: StateFlow<State<List<MovieModel>>>

    val topRatedMoviesState: StateFlow<State<List<MovieModel>>>

    suspend fun updatePopularMovies(): Effect<Completable>

    suspend fun updateUpcomingMovies(): Effect<Completable>

    suspend fun updateNowPlayingMovies(): Effect<Completable>

    suspend fun updateTopRatedMovies(): Effect<Completable>

    suspend fun getMovieDetails(id: Int): Effect<MovieDetailsModel>
}

class TmdbRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val api: TmdbApi,
) : TmdbRepository {

    private val _popularMoviesState = MutableStateFlow<State<List<MovieModel>>>(Loading())
    override val popularMoviesState = _popularMoviesState.asStateFlow()

    private val _upcomingMoviesState = MutableStateFlow<State<List<MovieModel>>>(Loading())
    override val upcomingMoviesState = _upcomingMoviesState.asStateFlow()

    private val _nowPlayingMoviesState = MutableStateFlow<State<List<MovieModel>>>(Loading())
    override val nowPlayingMoviesState = _nowPlayingMoviesState.asStateFlow()

    private val _topRatedMoviesState = MutableStateFlow<State<List<MovieModel>>>(Loading())
    override val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    override suspend fun updatePopularMovies(): Effect<Completable> {
        return apiCall(ioDispatcher) {
            api.getPopularMovies()
        }.map {
            it.toMoviesModel().movies
        }.also {
            _popularMoviesState tryPublish it
        }.toCompletable()
    }

    override suspend fun updateUpcomingMovies(): Effect<Completable> {
        return apiCall(ioDispatcher) {
            api.getUpcomingMovies(DEFAULT_LOCALE.language)
        }.map {
            it.toMoviesModel().movies
        }.also {
            _upcomingMoviesState tryPublish it
        }.toCompletable()
    }

    override suspend fun updateNowPlayingMovies(): Effect<Completable> {
        return apiCall(ioDispatcher) {
            api.getNowPlayingMovies(DEFAULT_LOCALE.language)
        }.map {
            it.toMoviesModel().movies
        }.also {
            _nowPlayingMoviesState tryPublish it
        }.toCompletable()
    }

    override suspend fun updateTopRatedMovies(): Effect<Completable> {
        return apiCall(ioDispatcher) {
            api.getTopRatedMovies(DEFAULT_LOCALE.language)
        }.map {
            it.toMoviesModel().movies
        }.also {
            _topRatedMoviesState tryPublish it
        }.toCompletable()
    }

    override suspend fun getMovieDetails(id: Int): Effect<MovieDetailsModel> {
        return apiCall(ioDispatcher) {
            api.getDetailsMovie(movieId = id)
        }.map {
            it.toMovieDetailsModel()
        }
    }
}