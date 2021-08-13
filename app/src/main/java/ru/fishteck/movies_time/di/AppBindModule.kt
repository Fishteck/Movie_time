package ru.fishteck.movies_time.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.fishteck.movies_time.data.local.GenresDataSource
import ru.fishteck.movies_time.data.local.GenresDataSourceImpl
import ru.fishteck.movies_time.data.local.MoviesDataSource
import ru.fishteck.movies_time.data.local.MoviesDataSourceImpl
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.data.repository.MovieRepositoryImpl
import ru.fishteck.movies_time.ui.movies.PopularMoviesViewModel


@Module
interface AppBindModule {
    @Binds
    fun bindMovieRepositoryImp_to_MovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    fun bindMoviesDataSourceImpl_to_MoviesDataSource(
        moviesDataSourceImpl : MoviesDataSourceImpl
    ) : MoviesDataSource

    @Binds
    fun bindGenresDataSourceImpl_to_GenresDataSource(
        genresDataSourceImpl: GenresDataSourceImpl
    ) : GenresDataSource

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    fun bindPopularMoviesViewModel(popularMoviesViewModel: PopularMoviesViewModel) : ViewModel


}