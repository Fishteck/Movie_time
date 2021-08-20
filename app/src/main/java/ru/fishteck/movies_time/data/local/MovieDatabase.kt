package ru.fishteck.movies_time.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fishteck.movies_time.data.local.dao.GenresDAO
import ru.fishteck.movies_time.data.local.dao.MoviesDAO
import ru.fishteck.movies_time.data.local.dao.ProfileDAO
import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.Converters

@Database(entities = [Movie::class, ProfileModel::class, GenreDto::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMoviesDAO() : MoviesDAO

    abstract fun getProfilesDAO() : ProfileDAO

    abstract fun getGenresDAO() : GenresDAO

    companion object {
        const val MOVIE_DATABASE_NAME = "Movie.db"
    }
}