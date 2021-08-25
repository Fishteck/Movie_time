package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDatesDto(
        @SerialName("iso_3166_1")
        val country: String,
        @SerialName("release_dates")
        val releaseDates: List<ReleaseDateDto>
)