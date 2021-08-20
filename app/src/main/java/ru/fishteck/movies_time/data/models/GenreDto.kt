package ru.fishteck.movies_time.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = GenreDto.GENRES_TABLE)
@Serializable
data class GenreDto(
     val name : String,
     @PrimaryKey(autoGenerate = false)
     val id : Int
) {
    companion object {
        const val GENRES_TABLE = "genres"
    }
}