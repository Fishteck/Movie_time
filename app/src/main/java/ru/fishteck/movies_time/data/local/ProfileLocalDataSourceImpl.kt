package ru.fishteck.movies_time.data.local

import ru.fishteck.movies_time.data.models.ProfileModel
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    private val profileDAO: ProfileDAO
) : ProfileLocalDataSource {

    override suspend fun loadProfile(id: Int): ProfileModel =
        profileDAO.loadProfile(id)

    override suspend fun addProfile(profile: ProfileModel) {
        profileDAO.addProfile(profile)
    }

    override suspend fun updateProfile(profile: ProfileModel) {
        profileDAO.updateProfile(profile)
    }

    override suspend fun deleteProfileById(id: Int) {
        profileDAO.deleteProfileById(id)
    }

}