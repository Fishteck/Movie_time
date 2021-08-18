package ru.fishteck.movies_time.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.utils.Converters

@Database(entities = [MovieModel::class, ProfileModel::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMoviesDAO() : MoviesDAO

    abstract fun getProfilesDAO() : ProfileDAO

    companion object {
        const val MOVIE_DATABASE_NAME = "Movie.db"
    }
}