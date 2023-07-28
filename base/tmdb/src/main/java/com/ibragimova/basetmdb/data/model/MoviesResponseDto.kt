package com.ibragimova.basetmdb.data.model

import com.ibragimova.corecommon.model.FullUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    @SerialName("results") val movies : List<MovieDto>,
)

@Serializable
data class MovieDto (
    @SerialName("backdrop_path") val backdropPath : FullUrl?,
    @SerialName("id") val id : Int,
    @SerialName("original_title") val originalTitle : String,
    @SerialName("poster_path") val posterPath : FullUrl?,
    @SerialName("title") val title : String,
    @SerialName("name") val name : String,
    @SerialName("media_type") val type: String,
)