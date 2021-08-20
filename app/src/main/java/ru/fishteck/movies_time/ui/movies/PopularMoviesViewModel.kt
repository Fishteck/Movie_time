package ru.fishteck.movies_time.ui.movies

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.fishteck.movies_time.data.models.GenreDto
import ru.fishteck.movies_time.data.models.MovieDto
import ru.fishteck.movies_time.data.repository.MovieRepository
import ru.fishteck.movies_time.models.Movie
import ru.fishteck.movies_time.utils.states.DataState
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
        private val repository: MovieRepository
) : ViewModel() {

    private val coroutineExceptionHandler: CoroutineExceptionHandler
    private val _popularMoviesState = MutableLiveData<DataState<List<Movie>?>>()
    val popularMoviesState: LiveData<DataState<List<Movie>?>> = _popularMoviesState
    private val _genreList = MutableLiveData<List<GenreDto>>()
    val genreList: LiveData<List<GenreDto>> = _genreList

    init {
        coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _popularMoviesState.postValue(DataState.Error(throwable.javaClass.simpleName))
            throwable?.message?.let { Log.e("PopularMoviesViewModel", it) }
        }
        getMovies()
        getGenres()
    }

    private fun getMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        _popularMoviesState.postValue(DataState.Loading)
        withContext(Dispatchers.IO) {
            _popularMoviesState.postValue(DataState.Success(repository.getMovies()))
        }
    }

    private fun getGenres() = viewModelScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
           _genreList.postValue(repository.getGenres())
        }
    }

    fun updateMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        _popularMoviesState.postValue(DataState.Loading)
        withContext(Dispatchers.IO) {
            _popularMoviesState.postValue(DataState.Success(repository.updateMovies()))
        }
    }

}