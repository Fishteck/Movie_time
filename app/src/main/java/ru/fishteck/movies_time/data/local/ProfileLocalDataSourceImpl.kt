package ru.fishteck.movies_time.data.local

import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    profileDAO: ProfileDAO
) : ProfileLocalDataSource {
}