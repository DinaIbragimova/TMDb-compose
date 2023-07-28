package com.ibragimova.corenavigation.base

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptionsBuilder
import com.ibragimova.corenavigation.DefaultArgKey
import com.ibragimova.corenavigation.RouteWithArg
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class FeatureNavDirection<ARG>(
    val route: RouteWithArg,
    val clazz: Class<ARG>,
    val arg: ARG,
    val optionsBuilder: NavOptionsBuilder.() -> Unit = {},
)

inline fun <reified ARG> FeatureNavDirection(
    route: RouteWithArg,
    arg: ARG,
    noinline optionsBuilder: NavOptionsBuilder.() -> Unit = {},
) = FeatureNavDirection(route, ARG::class.java, arg, optionsBuilder)

inline fun <reified ARG> ARG.toDefaultFormat() = Json.encodeToString(this)

inline fun <reified ARG> SavedStateHandle.getArg(): ARG? = get<String>(DefaultArgKey)?.let { Json.decodeFromString(it) }

inline fun <reified ARG> SavedStateHandle.requireArgs(): ARG = requireNotNull(getArg())