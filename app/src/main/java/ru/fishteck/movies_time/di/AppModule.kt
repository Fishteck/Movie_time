package ru.fishteck.movies_time.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Configuration
import dagger.Module
import dagger.Provides
import ru.fishteck.MovieApp
import ru.fishteck.movies_time.data.remote.IoschedWorkerFactory
import ru.fishteck.movies_time.utils.ApplicationContext
import ru.fishteck.movies_time.utils.MovieApiQualifier
import ru.fishteck.movies_time.utils.ViewModelFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [
    AppBindModule::class,
    MovieLocalModule::class,
    RepositoryBindModule::class,
    RemoteModule::class
])
class AppModule() {

    @Provides
    @ApplicationContext
    fun provideContext(application: MovieApp): Context {
        application.appComponent
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        ioschedWorkerFactory: IoschedWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(ioschedWorkerFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(
        providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>
    ) : ViewModelProvider.Factory {
        return ViewModelFactory(providerMap as MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    }
}