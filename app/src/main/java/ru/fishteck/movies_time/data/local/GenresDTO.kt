package ru.fishteck.movies_time.data.local

class GenresDTO (
    private val genresDataSource: GenresDataSource
        ) {
    fun getGenres() = genresDataSource.getGenres()
}