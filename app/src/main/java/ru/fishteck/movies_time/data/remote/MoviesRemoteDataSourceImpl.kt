package ru.fishteck.movies_time.data.remote

import ru.fishteck.movies_time.data.models.*
import ru.fishteck.movies_time.utils.MovieApiQualifier
import ru.fishteck.movies_time.utils.enums.Language
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
    @MovieApiQualifier private val apiKey: String
) : MoviesRemoteDataSource {

    override suspend fun getMovies(): List<MovieDto> =
        moviesService.getPopularMovies(apiKey, Language.RU.lang).moviesList

    override suspend fun getDetailMovie(id: Int): DetailMovieDto =
        moviesService.getDetailsMovie(id, apiKey, Language.RU.lang)

    override suspend fun getMovieCast(id: Int): List<CastDto> =
        moviesService.getCredits(id, apiKey, Language.RU.lang).cast

    override suspend fun getGenres(): List<GenreDto> =
        moviesService.getGenres(apiKey, Language.RU.lang).genres

    override suspend fun getReleaseDate(id: Int): List<ReleaseDatesDto> =
        moviesService.getReleaseDate(id, apiKey).results



}