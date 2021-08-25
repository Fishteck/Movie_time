package ru.fishteck.movies_time.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val page: Int,
    @SerialName("total_pages")
    val totalPages : Int,
    @SerialName("total_results")
    val totalResults : Int,
    @SerialName("results")
    val moviesList : List<MovieDto>
)