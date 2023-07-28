package com.ibragimova.tmdb.mainscreen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ibragimova.corenavigation.AppRoute
import com.ibragimova.corenavigation.MovieDetailsFeatureProvider
import com.ibragimova.corenavigation.MoviesFeatureProvider
import com.ibragimova.coreuicompose.tools.SystemBarsIconsColor
import com.ibragimova.coreuitheme.compose.AppTheme
import com.ibragimova.coreuitheme.compose.LocalStringHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class AppActivity : FragmentActivity() {

    @Inject
    lateinit var moviesFeatureProvider: MoviesFeatureProvider

    @Inject
    lateinit var movieDetailsFeatureProvider: MovieDetailsFeatureProvider

    private var shouldKeepSplashScreen = true

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().setKeepOnScreenCondition {
            shouldKeepSplashScreen
        }

        setContent {
            AppTheme(calculateWindowSizeClass(this)) {
                SideEffect {
                    shouldKeepSplashScreen = false
                }
                SystemBarsIconsColor()
                AppScreen()
            }
        }
    }

    @Composable
    private fun AppScreen() {
        val navController = rememberNavController()
        val viewModel = viewModel<AppViewModel>()
        val currentFlow by viewModel.currentFlowState.collectAsState()
        val stringHolder by viewModel.stringHolderState.collectAsState()

        CompositionLocalProvider(LocalStringHolder provides stringHolder) {
            when (currentFlow) {
                AppViewModel.CurrentFlow.Main -> MainFlow(navController)
            }
        }
    }

    @Composable
    private fun MainFlow(navController: NavHostController) {
        NavHost(navController = navController, startDestination = AppRoute.Movies.pattern) {
            with(movieDetailsFeatureProvider) { movieDetailsGraph(navController) }
            with(moviesFeatureProvider) { moviesGraph(navController) }
        }
    }
}