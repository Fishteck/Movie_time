package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.local.MoviesDataSource

class MoviesDTO(
    private val moviesDataSource: MoviesDataSource
) {
    fun getMovies() = moviesDataSource.getMovies()
}