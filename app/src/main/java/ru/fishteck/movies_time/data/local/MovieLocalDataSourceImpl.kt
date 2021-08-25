package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.local.dao.GenresDAO
import ru.fishteck.movies_time.data.local.dao.MoviesDAO
import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.data.models.MovieDto
import ru.fishteck.movies_time.models.Movie
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val moviesDAO: MoviesDAO,
    private val genresDAO: GenresDAO
    ) : MovieLocalDataSource {

    override suspend fun getMovies(): List<Movie> =
        moviesDAO.loadAllMovies()

    override suspend fun addAllMovies(movies: List<Movie>?) {
        moviesDAO.addAllMovies(movies)
    }

    override suspend fun deleteMovies() {
        moviesDAO.deleteAllMovies()
    }

    override suspend fun getGenres(): List<GenreDto> =
        genresDAO.loadAllGenres()

    override suspend fun addGenres(genres: List<GenreDto>) {
        genresDAO.addAllGenres(genres)
    }



}