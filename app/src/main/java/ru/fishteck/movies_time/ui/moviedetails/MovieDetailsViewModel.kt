package ru.fishteck.movies_time.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.fishteck.movies_time.data.repository.MovieRepository

class MovieDetailsViewModel(
    private val movieId: Int,
    private val repository: MovieRepository
) : ViewModel()

class MovieDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("movieId") private val movieId: Int,
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsViewModel::class)
        return MovieDetailsViewModel(movieId, repository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("movieId") movieId: Int): MovieDetailsViewModelFactory
    }

}