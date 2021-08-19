package ru.fishteck.movies_time.data.repository

import kotlinx.coroutines.delay
import ru.fishteck.movies_time.data.local.GenresDataSource
import ru.fishteck.movies_time.data.local.MovieLocalDataSource
import ru.fishteck.movies_time.data.remote.MoviesRemoteDataSource
import ru.fishteck.movies_time.data.models.GenreModel
import ru.fishteck.movies_time.data.models.MovieModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val genresDataSource: GenresDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
    ) : MovieRepository {
    override suspend fun getMovies() : List<MovieModel>  {
        delay(2000)
        return moviesRemoteDataSource.getMovies().shuffled()
    }

    override suspend fun getDetailMovie(id: Int): MovieModel {
        return moviesRemoteDataSource.getMovies().first{ it.id == id }
    }

    override fun getGenres(): List<GenreModel> =
        genresDataSource.getGenres()

    override suspend fun getLocalMovies(): List<MovieModel> {
        return if (movieLocalDataSource.getMovies().isEmpty()) {
            movieLocalDataSource.addAllMovies(moviesRemoteDataSource.getMovies())
            movieLocalDataSource.getMovies()
        } else {
            movieLocalDataSource.getMovies()
        }
    }


    override suspend fun addAllMovies(movies: List<MovieModel>) {
        movieLocalDataSource.addAllMovies(movies)
    }
}