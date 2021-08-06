package ru.fishteck.movies_time.data.repository

import kotlinx.coroutines.delay
import ru.fishteck.movies_time.data.local.GenresDataSource
import ru.fishteck.movies_time.data.local.MoviesDataSource
import ru.fishteck.movies_time.data.models.GenreModel
import ru.fishteck.movies_time.data.models.MovieModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val genresDataSource: GenresDataSource
    ) : MovieRepository {
    override suspend fun getMovies() : List<MovieModel>  {
        delay(2000)
        return moviesDataSource.getMovies().shuffled()
    }

    override fun getGenres(): List<GenreModel> =
        genresDataSource.getGenres()
}