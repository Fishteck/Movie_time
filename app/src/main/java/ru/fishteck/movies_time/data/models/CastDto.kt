package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    val name: String,
    val id: Int,
    @SerialName("profile_path")
    val photo: String?
)
