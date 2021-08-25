package ru.fishteck.movies_time.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Movie.MOVIES_TABLE)
data class Movie(
        val title: String?,
        val description: String?,
        val imageUrl: String?,
        val rateScore: Float,
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val ageRestriction : String
) {
    companion object {
        const val MOVIES_TABLE = "movies"
    }
}
