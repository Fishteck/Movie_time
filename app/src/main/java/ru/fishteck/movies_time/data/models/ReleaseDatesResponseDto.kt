package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDatesResponseDto(
        val id: Int,
        @SerialName("results")
        val results: List<ReleaseDatesDto>
)
