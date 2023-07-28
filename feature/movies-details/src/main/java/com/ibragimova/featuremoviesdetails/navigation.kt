package com.ibragimova.featuremoviesdetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ibragimova.corenavigation.AppRoute
import com.ibragimova.corenavigation.MovieDetailsFeatureProvider
import com.ibragimova.corenavigation.base.Navigator
import com.ibragimova.corenavigation.base.composable
import com.ibragimova.featuremoviesdetails.presentation.screen.MovieDetailsScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController)

class MovieDetailsFeatureProviderImpl @Inject constructor() : MovieDetailsFeatureProvider {

    override fun NavGraphBuilder.movieDetailsGraph(controller: NavHostController) {

        composable(AppRoute.MoviesDetails) { MovieDetailsScreen(navHostController = controller) }
    }
}