package ru.fishteck.movies_time.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.fishteck.MovieApp
import ru.fishteck.movies_time.ui.moviedetails.MovieDetailsFragment
import ru.fishteck.movies_time.ui.movies.PopularMoviesFragment
import ru.fishteck.movies_time.ui.profile.LogInFragment
import ru.fishteck.movies_time.ui.profile.ProfileFragment
import ru.fishteck.movies_time.utils.MovieApiQualifier
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
     fun inject(fragment: PopularMoviesFragment)
     fun inject(fragment: MovieDetailsFragment)
     fun inject(fragment: ProfileFragment)
     fun inject(fragment: LogInFragment)

     @Component.Builder
     interface Builder {

          @BindsInstance
          fun application(application: MovieApp) : Builder

          @BindsInstance
          fun apiKey(@MovieApiQualifier apiKey : String) : Builder

          fun build() : AppComponent
     }

}