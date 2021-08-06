package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.GenreModel
import javax.inject.Inject

class GenresDataSourceImpl @Inject constructor() : GenresDataSource {
    override fun getGenres() = listOf(
        GenreModel("Боевик", 28),
        GenreModel("Приключения", 12),
        GenreModel("Мультфильм", 16),
        GenreModel("Комедия", 35),
        GenreModel("Криминал", 80),
        GenreModel("Документальное", 99),
        GenreModel("Драма", 18),
        GenreModel("Семейный", 10751),
        GenreModel("Фантастика", 14),
        GenreModel("Исторический", 36),
        GenreModel("Ужасы", 27),
        GenreModel("Музыка", 10402),
        GenreModel("Мистика", 9648),
        GenreModel("Романтика", 10749),
        GenreModel("Научная фантастика", 879),
        GenreModel("Триллер", 53),
        GenreModel("Военный", 10752),
        GenreModel("Вестерн", 37),
        GenreModel("Рельное ТВ", 10770)

    )
}