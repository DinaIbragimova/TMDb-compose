package com.ibragimova.corenavigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ibragimova.corecommon.container.IconValue
import com.ibragimova.corecommon.strings.StringKey

interface MoviesFeatureProvider {

    fun NavGraphBuilder.moviesGraph(controller: NavHostController)
}

interface MovieDetailsFeatureProvider {

    fun NavGraphBuilder.movieDetailsGraph(controller: NavHostController)
}