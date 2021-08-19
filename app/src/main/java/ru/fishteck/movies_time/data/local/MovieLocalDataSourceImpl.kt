package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.MovieModel
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val moviesDAO: MoviesDAO
    ) : MovieLocalDataSource {
    override suspend fun getMovies(): List<MovieModel> =
        moviesDAO.loadAllMovies()


    override suspend fun addAllMovies(movies : List<MovieModel>) {
        moviesDAO.addAllMovies(movies)
    }
}