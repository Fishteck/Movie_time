package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDateDto(
        @SerialName("certification")
        val ageRestriction: String,
        @SerialName("release_date")
        val releaseDate: String,
        )
