package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.MovieModel

interface MovieLocalDataSource {
    suspend fun getMovies() : List<MovieModel>
    suspend fun addAllMovies(movies : List<MovieModel>)
}