package ru.fishteck.movies_time.utils

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.fishteck.movies_time.data.remote.MovieWorker
import ru.fishteck.movies_time.data.repository.MovieRepository


class MovieWorkerFactory (
    private val repository: MovieRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            MovieWorker::class.java.name ->
                MovieWorker(appContext, workerParameters, repository)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}