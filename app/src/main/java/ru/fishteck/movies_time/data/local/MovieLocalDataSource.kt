package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.models.Movie

interface MovieLocalDataSource {
    suspend fun getMovies() : List<Movie>
    suspend fun addAllMovies(movies : List<Movie>?)
    suspend fun deleteMovies()
    suspend fun getGenres() : List<GenreDto>
    suspend fun addGenres(genres : List<GenreDto>)
}