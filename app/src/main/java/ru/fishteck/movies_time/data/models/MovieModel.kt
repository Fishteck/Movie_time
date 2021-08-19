package ru.fishteck.movies_time.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieModel.MOVIES_TABLE)
data class MovieModel(
        val title: String?,
        val description: String?,
        val rateScore: Int,
        val ageRestriction: Int,
        val imageUrl: String?,

        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val genre: String
) {

    companion object {
        const val MOVIES_TABLE = "movies"
    }
}
