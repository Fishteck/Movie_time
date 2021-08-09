package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.data.models.GenreModel
import ru.fishteck.movies_time.data.models.MovieModel

interface MovieRepository {
    suspend fun getMovies() : List<MovieModel>
    suspend fun getDetailMovie(id : Int) : MovieModel
    fun getGenres() : List<GenreModel>
}