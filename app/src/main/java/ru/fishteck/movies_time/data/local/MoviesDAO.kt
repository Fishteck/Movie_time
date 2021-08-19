package ru.fishteck.movies_time.data.local

import androidx.room.*
import ru.fishteck.movies_time.data.models.MovieModel

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM ${MovieModel.MOVIES_TABLE}")
    fun loadAllMovies() : List<MovieModel>

    @Query("SELECT * FROM ${MovieModel.MOVIES_TABLE} WHERE `id` like :id")
    fun loadMovie(id : Int) : MovieModel

    @Delete
    fun deleteMovie(movie: MovieModel)

    @Query("DELETE FROM ${MovieModel.MOVIES_TABLE} WHERE `id` like :id")
    fun deleteMovieById(id : Int)

    @Query("DELETE FROM ${MovieModel.MOVIES_TABLE}")
    fun deleteAllMovies()

    @Insert(entity = MovieModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: MovieModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMovies(movies: List<MovieModel>)
}