package ru.fishteck.movies_time.utils

import java.lang.Exception

fun Float.ratingConverter() : Float {
    return try {
        this / 2
    } catch (ex : Exception) {
        0f
    }
}