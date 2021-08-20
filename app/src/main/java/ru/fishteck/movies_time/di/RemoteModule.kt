package ru.fishteck.movies_time.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.fishteck.movies_time.data.remote.MoviesService
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").client(okHttpClient)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}