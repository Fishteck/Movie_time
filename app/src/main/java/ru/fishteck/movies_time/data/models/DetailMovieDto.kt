package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailMovieDto (
        val title: String?,
        @SerialName("overview")
        val description: String?,
        @SerialName("poster_path")
        val imageUrl: String?,
        @SerialName("vote_average")
        val rateScore: Float,
        val id: Int,
        @SerialName("genres")
        val genres : List<GenreDto>? = null
        )