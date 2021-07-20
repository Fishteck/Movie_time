package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.GenreModel


interface GenresDataSource {
    fun getGenres(): List<GenreModel>
}