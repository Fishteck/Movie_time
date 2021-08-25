package ru.fishteck.movies_time.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.fishteck.movies_time.data.models.GenreDto

@Dao
interface GenresDAO {

    @Query("SELECT * FROM ${GenreDto.GENRES_TABLE}")
    fun loadAllGenres(): List<GenreDto>

    @Query("DELETE FROM ${GenreDto.GENRES_TABLE}")
    fun deleteAllGenres()

    @Insert(entity = GenreDto::class, onConflict = OnConflictStrategy.REPLACE)
    fun addAllGenres(movies: List<GenreDto>)
}