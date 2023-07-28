package com.ibragimova.coredata.network

import com.ibragimova.corecommon.model.BuildInfo
import java.lang.IllegalStateException

enum class ApiService(
    val prod: String,
    val test: String = prod,
) {

    General("https://api.themoviedb.org/3/");

    fun getBaseUrl(buildInfo: BuildInfo) = when {
        buildInfo.isDebug || buildInfo.isAlpha -> test
        buildInfo.isRelease -> prod
        else -> throw IllegalStateException("Unknown build type")
    }
}