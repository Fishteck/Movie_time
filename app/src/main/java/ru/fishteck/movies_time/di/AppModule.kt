package ru.fishteck.movies_time.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.fishteck.MovieApp
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [AppBindModule::class, MovieLocalModule::class])
class AppModule(private val application: MovieApp) {

    @Provides
    @Singleton
    fun provideMovieApp() : MovieApp = application

    @Provides
    @ApplicationContext
    fun provideContext(application: MovieApp): Context {
        application.appComponent
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(
        providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>
    ) : ViewModelProvider.Factory {
        return ViewModelFactory(providerMap as MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    }
}