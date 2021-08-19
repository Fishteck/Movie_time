package ru.fishteck.movies_time.data.remote

import ru.fishteck.movies_time.data.models.MovieModel

interface MoviesRemoteDataSource {
    suspend fun getMovies(): List<MovieModel>
}