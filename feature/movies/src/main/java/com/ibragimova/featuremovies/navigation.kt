package com.ibragimova.featuremovies

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ibragimova.corenavigation.AppRoute
import com.ibragimova.corenavigation.MoviesFeatureProvider
import com.ibragimova.corenavigation.base.FeatureNavDirection
import com.ibragimova.corenavigation.base.Navigator
import com.ibragimova.corenavigation.base.composable
import com.ibragimova.corenavigation.base.navigate
import com.ibragimova.featuremovies.presentation.screen.MoviesScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toMoviesDetailsScreen(
        id: Int,
    ) = navHostController navigate FeatureNavDirection(
        route = AppRoute.MoviesDetails,
        arg = AppRoute.MoviesDetails.Args(id),
    )
}

class MoviesFeatureProviderImpl @Inject constructor() : MoviesFeatureProvider {
    override fun NavGraphBuilder.moviesGraph(controller: NavHostController) {
        composable(AppRoute.Movies) { MoviesScreen(navHostController = controller) }
    }
}