package com.ibragimova.basetmdb.data.model

import com.ibragimova.corecommon.model.DateTime
import com.ibragimova.corecommon.model.FullUrl
import com.ibragimova.corecommon.model.Id
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponseDto(
    @SerialName("backdrop_path") val backdropPath: FullUrl?,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("id") val id: Int,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: FullUrl?,
    @SerialName("release_date") val releaseDate: DateTime,
    @SerialName("revenue") val revenue: Int,
    @SerialName("runtime") val runtime: Int,
    @SerialName("title") val title: String,
    @SerialName("first_air_date") val firstAirDate: DateTime,
)

@Serializable
data class Genre(
    @SerialName("name") val name: String,
)