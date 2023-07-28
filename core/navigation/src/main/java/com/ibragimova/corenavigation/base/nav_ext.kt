package com.ibragimova.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.ibragimova.corenavigation.Route

fun NavGraphBuilder.composable(
    route: Route,
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route = route.pattern,
    arguments = route.arguments,
    content = content,
)

fun NavController.navigate(route: Route, builder: NavOptionsBuilder.() -> Unit = {}) = navigate(
    route = route.path(),
    builder = builder,
)

inline infix fun <reified ARG> NavController.navigate(direction: FeatureNavDirection<ARG>) = navigate(
    route = direction.route.path(direction.arg.toDefaultFormat()),
    builder = direction.optionsBuilder,
)