package ru.fishteck.movies_time.data.local.dao

import androidx.room.*
import ru.fishteck.movies_time.data.models.ProfileModel

@Dao
interface ProfileDAO {

    @Query("SELECT * FROM ${ProfileModel.PROFILES_TABLE}")
    fun loadAllProfiles(): List<ProfileModel>

    @Query("SELECT * FROM ${ProfileModel.PROFILES_TABLE} WHERE `id` like :id")
    fun loadProfile(id: Int): ProfileModel

    @Query("DELETE FROM ${ProfileModel.PROFILES_TABLE} WHERE `id` like :id")
    fun deleteProfileById(id: Int)

    @Insert(entity = ProfileModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun addProfile(profile: ProfileModel)

    @Update(entity = ProfileModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateProfile(profile: ProfileModel)


}