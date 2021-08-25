package ru.fishteck.movies_time.utils.states

sealed class ProfileState<out T> {
    class Error(val message : String) : ProfileState<Nothing>()
    class Success<T>(val data : T) : ProfileState<T>()
}
