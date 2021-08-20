package ru.fishteck.movies_time.utils.extension

import ru.fishteck.movies_time.data.models.*
import ru.fishteck.movies_time.models.DetailMovie
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.ratingConverter

fun DetailMovieDto.toDetailMovie(cast: List<CastDto>, releaseDates: List<ReleaseDatesDto>): DetailMovie {
    return DetailMovie(
            title = this.title,
            description = this.description,
            imageUrl = "https://image.tmdb.org/t/p/w500/" + this.imageUrl,
            rateScore = this.rateScore.ratingConverter(),
            id = this.id,
            genre = this.genres ?: emptyList(),
            cast = cast,
            ageRestriction = getAgeLimit(releaseDates)

    )
}

fun MovieDto.toMovie(releaseDates: List<ReleaseDatesDto>): Movie {
    return Movie(
            title = this.title,
            description = this.description,
            imageUrl = "https://image.tmdb.org/t/p/w200/" + this.imageUrl,
            rateScore = this.rateScore.ratingConverter(),
            id = this.id,
            ageRestriction = getAgeLimit(releaseDates)
    )
}

private fun getAgeLimit(releaseDates: List<ReleaseDatesDto>) : String {
    var ageRestriction: String = "12+"

    releaseDates.forEach {
        if (it.country.equals("RU")) {
            for (el: ReleaseDateDto in it.releaseDates ) {
                ageRestriction = if (el.ageRestriction.isNotEmpty() && el.ageRestriction != "")
                    el.ageRestriction
                else
                    "12+"
            }
        }
    }
    return ageRestriction
}

