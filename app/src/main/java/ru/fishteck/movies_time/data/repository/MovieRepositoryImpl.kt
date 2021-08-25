package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.data.local.MovieLocalDataSource
import ru.fishteck.movies_time.data.models.CastDto
import ru.fishteck.movies_time.models.DetailMovie
import ru.fishteck.movies_time.data.remote.MoviesRemoteDataSource
import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.data.models.ReleaseDatesDto
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.extension.toDetailMovie
import ru.fishteck.movies_time.utils.extension.toMovie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val moviesRemoteDataSource: MoviesRemoteDataSource,
        private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override suspend fun getDetailMovie(id: Int): DetailMovie =
            moviesRemoteDataSource.getDetailMovie(id).toDetailMovie(
                    moviesRemoteDataSource.getMovieCast(id),
                    moviesRemoteDataSource.getReleaseDate(id)
            )

    override suspend fun updateMovies(): List<Movie>? {
        val list = moviesRemoteDataSource.getMovies()?.map {
            it.toMovie(
                    moviesRemoteDataSource.getReleaseDate(it.id)
            )
        }
        movieLocalDataSource.deleteMovies()
        movieLocalDataSource.addAllMovies(list)

        return list
    }

    override suspend fun getGenres(): List<GenreDto> {
        return if (movieLocalDataSource.getGenres().isEmpty()) {
            val list = moviesRemoteDataSource.getGenres()
            movieLocalDataSource.addGenres(list)
            list
        } else {
            movieLocalDataSource.getGenres()
        }
    }

    override suspend fun getMovies(): List<Movie>? {

        return if (movieLocalDataSource.getMovies().isEmpty()) {
            val list = moviesRemoteDataSource.getMovies()?.map {
                it.toMovie(
                        moviesRemoteDataSource.getReleaseDate(it.id)
                )
            }
            movieLocalDataSource.addAllMovies(list)

            list
        } else {
            movieLocalDataSource.getMovies()
        }
    }

}