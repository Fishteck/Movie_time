package ru.fishteck.movies_time.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseDto (
    val genres : List<GenreDto>
)