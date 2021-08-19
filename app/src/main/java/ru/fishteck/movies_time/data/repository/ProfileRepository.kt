package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.data.models.ProfileModel

interface ProfileRepository {
    suspend fun loadProfile(id: Int): ProfileModel
    suspend fun addProfile(profile: ProfileModel)
    suspend fun updateProfile(profile: ProfileModel)
    suspend fun deleteProfileById(id: Int)
}