package ru.fishteck

import android.app.Application
import android.content.Context
import ru.fishteck.movies_time.di.AppComponent
import ru.fishteck.movies_time.di.DaggerAppComponent

class MovieApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MovieApp -> appComponent
        else -> applicationContext.appComponent
    }