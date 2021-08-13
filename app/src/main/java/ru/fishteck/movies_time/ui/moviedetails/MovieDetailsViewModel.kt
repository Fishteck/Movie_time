package ru.fishteck.movies_time.ui.moviedetails

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.utils.DataState

class MovieDetailsViewModel(
    private val movieId: Int,
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieDetailState: LiveData<DataState<MovieModel>> = liveData(Dispatchers.IO) {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(repository.getDetailMovie(movieId)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex.message.toString()))
        }
    }

    val movieDetailState: LiveData<DataState<MovieModel>> = _movieDetailState

}

class MovieDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("movieId") private val movieId: Int,
    private val repository: MovieRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsViewModel::class.java)
        return MovieDetailsViewModel(movieId, repository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("movieId") movieId: Int): MovieDetailsViewModelFactory
    }

}