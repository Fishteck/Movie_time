package ru.fishteck.movies_time.models

import ru.fishteck.movies_time.data.models.CastDto
import ru.fishteck.movies_time.data.models.GenreDto

data class DetailMovie(
        val title: String?,
        val description: String?,
        val imageUrl: String?,
        val rateScore: Float,
        val id: Int,
        val genre: List<GenreDto>,
        val cast: List<CastDto>,
        val ageRestriction: String
) {

}