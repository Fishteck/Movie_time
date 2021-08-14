package ru.fishteck.movies_time.data.repository

import ru.fishteck.movies_time.data.local.ProfileLocalDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    profileLocalDataSource: ProfileLocalDataSource
) : ProfileRepository{
}