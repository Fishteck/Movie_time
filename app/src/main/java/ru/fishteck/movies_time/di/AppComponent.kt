package ru.fishteck.movies_time.di

import dagger.Component
import ru.fishteck.movies_time.ui.moviedetails.MovieDetailsFragment
import ru.fishteck.movies_time.ui.movies.PopularMoviesFragment

@Component(modules = [AppModule::class])
interface AppComponent {
     fun inject(fragment: PopularMoviesFragment)
     fun inject(fragment: MovieDetailsFragment)

}