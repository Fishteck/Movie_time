package ru.fishteck.movies_time.data.local.dao

import androidx.room.*
import ru.fishteck.movies_time.data.models.MovieDto
import ru.fishteck.movies_time.models.Movie

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM ${Movie.MOVIES_TABLE}")
    fun loadAllMovies(): List<Movie>

    @Query("SELECT * FROM ${Movie.MOVIES_TABLE} WHERE `id` like :id")
    fun loadMovie(id: Int): Movie

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM ${Movie.MOVIES_TABLE} WHERE `id` like :id")
    fun deleteMovieById(id: Int)

    @Query("DELETE FROM ${Movie.MOVIES_TABLE}")
    fun deleteAllMovies()

    @Insert(entity = Movie::class, onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMovies(movies: List<Movie>?)
}