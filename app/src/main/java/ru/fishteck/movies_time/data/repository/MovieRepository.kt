package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.models.DetailMovie
import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.models.Movie

interface MovieRepository {
    suspend fun getMovies() : List<Movie>?
    suspend fun getDetailMovie(id : Int) : DetailMovie
    suspend fun updateMovies() : List<Movie>?
    suspend fun getGenres() : List<GenreDto>
}