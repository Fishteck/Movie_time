package ru.fishteck.movies_time.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.utils.Converters

@Database(entities = [ProfileModel::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProfileDatabase : RoomDatabase(){
    abstract fun getProfilesDAO() : ProfileDAO

    companion object {
        const val PROFILE_DATABASE_NAME = "Profile.db"
    }
}