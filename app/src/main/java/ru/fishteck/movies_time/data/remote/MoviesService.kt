package ru.fishteck.movies_time.data.remote

import androidx.annotation.IntRange
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.fishteck.movies_time.data.models.*

interface MoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") @IntRange(from = 1) page : Int = 1
    ): MoviesResponseDto

    @GET("movie/{id}")
    suspend fun getDetailsMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): DetailMovieDto

    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): CreditsResponseDto

    @GET("genre/movie/list")
    suspend fun getGenres(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ) : GenresResponseDto

    @GET("movie/{id}/release_dates")
    suspend fun getReleaseDate(
            @Path("id") id: Int,
            @Query("api_key") apiKey: String
    ) : ReleaseDatesResponseDto
}