package ru.fishteck.movies_time.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.fishteck.MovieApp
import ru.fishteck.movies_time.utils.ApplicationContext
import ru.fishteck.movies_time.utils.ViewModelFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [
    AppBindModule::class,
    MovieLocalModule::class,
    RepositoryBindModule::class
])
class AppModule() {

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