package ru.fishteck.movies_time.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.fishteck.movies_time.data.local.MovieDatabase
import ru.fishteck.movies_time.data.local.MoviesDAO
import ru.fishteck.movies_time.data.local.ProfileDAO
import ru.fishteck.movies_time.utils.ApplicationContext
import javax.inject.Singleton

@Module
class MovieLocalModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.MOVIE_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMovieDAO(database: MovieDatabase): MoviesDAO =
        database.getMoviesDAO()

    @Provides
    @Singleton
    fun provideProfileDAO(database: MovieDatabase): ProfileDAO =
        database.getProfilesDAO()
}