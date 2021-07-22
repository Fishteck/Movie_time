package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.MovieModel

interface MoviesDataSource {
    fun getMovies(): List<MovieModel>
}