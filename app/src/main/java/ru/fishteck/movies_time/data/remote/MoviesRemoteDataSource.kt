package ru.fishteck.movies_time.data.remote

import ru.fishteck.movies_time.data.models.*

interface MoviesRemoteDataSource {
    suspend fun getMovies(): List<MovieDto>?
    suspend fun getDetailMovie(id: Int): DetailMovieDto
    suspend fun getMovieCast(id: Int): List<CastDto>
    suspend fun getGenres() : List<GenreDto>
    suspend fun getReleaseDate(id: Int) : List<ReleaseDatesDto>
}