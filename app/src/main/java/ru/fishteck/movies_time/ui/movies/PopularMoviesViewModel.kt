package ru.fishteck.movies_time.ui.movies

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.fishteck.movies_time.data.models.MovieModel
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.utils.DataState
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
        private val repository: MovieRepository
) : ViewModel() {

    private val coroutineExceptionHandler: CoroutineExceptionHandler
    private val _popularMoviesState = MutableLiveData<DataState<List<MovieModel>>>()
    val popularMoviesState: LiveData<DataState<List<MovieModel>>> = _popularMoviesState

    init {
        coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _popularMoviesState.postValue(DataState.Error(throwable.javaClass.simpleName))
        }
        getMovies()
    }

    fun getMovies() = viewModelScope.launch(coroutineExceptionHandler) {

        _popularMoviesState.postValue(DataState.Loading)

        withContext(Dispatchers.IO) {
            _popularMoviesState.postValue(DataState.Success(repository.getMovies()))
        }
    }

    fun getGenres() = repository.getGenres()

}