package ru.fishteck.movies_time.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fishteck.movies_time.data.models.MovieModel

@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDAO() : MoviesDAO

    companion object {
        const val MOVIE_DATABASE_NAME = "Movie.db"
    }
}