package ru.fishteck.movies_time.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.fishteck.movies_time.data.local.*
import ru.fishteck.movies_time.data.remote.MoviesRemoteDataSource
import ru.fishteck.movies_time.data.remote.MoviesRemoteDataSourceImpl
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.data.repository.MovieRepositoryImpl
import ru.fishteck.movies_time.data.repository.ProfileRepository
import ru.fishteck.movies_time.data.repository.ProfileRepositoryImpl
import ru.fishteck.movies_time.ui.movies.PopularMoviesViewModel
import ru.fishteck.movies_time.ui.profile.ProfileViewModel
import ru.fishteck.movies_time.utils.ViewModelKey


@Module
interface AppBindModule {


    @Binds
    fun bindProfileLocalDataSourceImpl_to_ProfileLocalDataSource(
        profileLocalDataSourceImpl: ProfileLocalDataSourceImpl
    ): ProfileLocalDataSource

    @Binds
    fun bindGenresDataSourceImpl_to_GenresDataSource(
        genresDataSourceImpl: GenresDataSourceImpl
    ): GenresDataSource

    @Binds
    fun bindMovieLocalDataSourceImpl_to_MovieLocalDataSource(
        movieLocalDataSource: MovieLocalDataSourceImpl
    ): MovieLocalDataSource

    @Binds
    fun bindMoviesRemoteDataSourceImpl_to_MoviesDataSource(
        moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    fun bindPopularMoviesViewModel(popularMoviesViewModel: PopularMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileMoviesViewModel(profileViewModel: ProfileViewModel): ViewModel

}