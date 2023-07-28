package com.ibragimova.corenavigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable

const val DefaultArgKey = "arg"

sealed class Route(protected val value: String) {

    open val pattern: String = value
    open val arguments: List<NamedNavArgument> = emptyList()

    open fun path(vararg args: Any): String = value
}

sealed class RouteWithArg(value: String) : Route(value) {

    override val pattern = "$value?$DefaultArgKey={$DefaultArgKey}"
    override val arguments = listOf(navArgument(DefaultArgKey) { type = NavType.StringType })

    override fun path(vararg args: Any): String = "$value?$DefaultArgKey=${args.first()}"
}

object AppRoute {

    object Movies : Route("Movies")

    object MoviesDetails : RouteWithArg("MoviesDetails") {
        @Serializable
        data class Args(val id: Int)
    }
}