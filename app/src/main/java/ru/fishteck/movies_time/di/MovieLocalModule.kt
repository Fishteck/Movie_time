package ru.fishteck.movies_time.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.fishteck.MovieApp
import ru.fishteck.appComponent
import ru.fishteck.movies_time.data.local.MovieDatabase
import ru.fishteck.movies_time.data.local.MoviesDAO
import ru.fishteck.movies_time.data.local.ProfileDAO
import ru.fishteck.movies_time.data.local.ProfileDatabase
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
    fun providesProfileDatabase(@ApplicationContext context: Context): ProfileDatabase =
        Room.databaseBuilder(
            context,
            ProfileDatabase::class.java,
            ProfileDatabase.PROFILE_DATABASE_NAME
        )
            .build()

    @Provides
    @Singleton
    fun provideProfileDAO(database: ProfileDatabase): ProfileDAO =
        database.getProfilesDAO()
}