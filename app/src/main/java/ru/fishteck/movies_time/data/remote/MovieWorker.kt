package ru.fishteck.movies_time.data.remote

import android.content.Context
import androidx.work.*
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.utils.MovieWorkerFactory
import javax.inject.Inject
import javax.inject.Singleton

class MovieWorker constructor(
    appContext: Context,
    params: WorkerParameters,
    private val repository: MovieRepository
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            repository.updateMovies()
            Result.success()
        } catch (ex : Exception) {
            Result.failure()
        }
    }


}

@Singleton
class IoschedWorkerFactory @Inject constructor(
    repository: MovieRepository
) : DelegatingWorkerFactory() {
    init {
        addFactory(MovieWorkerFactory(repository))
    }
}