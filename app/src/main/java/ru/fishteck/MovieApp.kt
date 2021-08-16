package ru.fishteck

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import ru.fishteck.movies_time.BuildConfig
import ru.fishteck.movies_time.di.AppComponent
import ru.fishteck.movies_time.di.AppModule
import ru.fishteck.movies_time.di.DaggerAppComponent


class MovieApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    lateinit var encPrefs: SharedPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .apiKey(BuildConfig.MOVIE_API_KEY)
            .build()

        val masterKey = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MasterKey.Builder(this)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        } else {
            MasterKey.Builder(applicationContext)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        }

         encPrefs = EncryptedSharedPreferences.create(
            applicationContext,
            "ENCRYPTED_PREF_FILE_NAME",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MovieApp -> appComponent
        else -> applicationContext.appComponent
    }



val Context.encPrefs: SharedPreferences
    get() = when (this) {
        is MovieApp -> encPrefs
        else -> applicationContext.encPrefs
    }

