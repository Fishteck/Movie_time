package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.data.local.ProfileLocalDataSource
import ru.fishteck.movies_time.data.models.ProfileModel
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource
) : ProfileRepository{
    override suspend fun loadProfile(id: Int): ProfileModel =
        profileLocalDataSource.loadProfile(id)

    override suspend fun addProfile(profile: ProfileModel) {
        profileLocalDataSource.addProfile(profile)
    }

    override suspend fun updateProfile(profile: ProfileModel) {
        profileLocalDataSource.updateProfile(profile)
    }

    override suspend fun deleteProfileById(id: Int) {
        profileLocalDataSource.deleteProfileById(id)
    }
}