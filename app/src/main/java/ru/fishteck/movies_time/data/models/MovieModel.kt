package ru.fishteck.movies_time.data.models

data class MovieModel (
        val title: String,
        val description: String,
        val rateScore: Int,
        val ageRestriction: Int,
        val imageUrl: String,
        val id : Int
        )