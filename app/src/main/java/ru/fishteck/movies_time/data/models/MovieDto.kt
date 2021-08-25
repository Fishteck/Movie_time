package ru.fishteck.movies_time.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDto(
        val title: String?,
        @SerialName("overview")
        val description: String?,
        @SerialName("poster_path")
        val imageUrl: String?,
        @SerialName("vote_average")
        val rateScore: Float,
        val id: Int
)
