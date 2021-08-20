package ru.fishteck.movies_time.utils.states

sealed class DataState<out T> {
    class Error(val message : String) : DataState<Nothing>()
    class Success<T>(val data : T) : DataState<T>()
    object Loading : DataState<Nothing>()
}