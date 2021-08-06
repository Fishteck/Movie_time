package ru.fishteck.movies_time.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.fishteck.movies_time.ui.movies.PopularMoviesViewModel
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [AppBindModule::class])
class AppModule {


    @Provides
    @Singleton
    fun provideViewModelFactory(
        providerMap : Map<Class<out ViewModel>, Provider<ViewModel>>
    ) : ViewModelProvider.Factory {
        return ViewModelFactory(providerMap as MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    }
}