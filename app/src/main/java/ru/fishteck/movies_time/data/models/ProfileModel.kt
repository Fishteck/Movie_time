package ru.fishteck.movies_time.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProfileModel.PROFILES_TABLE)
data class ProfileModel(
     val userName : String,
     val userEmail : String,
     val interest : List<String>,
     val mobileNumber : String,
    @PrimaryKey(autoGenerate = true)
     val id : Int
) {

    companion object {
        const val PROFILES_TABLE = "profiles"
    }
}