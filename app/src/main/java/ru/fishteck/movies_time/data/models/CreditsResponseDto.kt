package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<CastDto>
)