package ru.fishteck.movies_time.di

import dagger.Binds
import dagger.Module
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.data.repository.MovieRepositoryImpl
import ru.fishteck.movies_time.data.repository.ProfileRepository
import ru.fishteck.movies_time.data.repository.ProfileRepositoryImpl

@Module
interface RepositoryBindModule {
    @Binds
    fun bindMovieRepositoryImp_to_MovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    fun bindProfileRepositoryImpl_to_ProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}