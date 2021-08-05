package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.MovieModel

interface MoviesDataSource {
    suspend fun getMovies(): List<MovieModel>
}