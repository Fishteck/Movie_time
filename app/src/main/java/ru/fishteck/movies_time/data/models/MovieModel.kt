package ru.fishteck.movies_time.data.models

import android.os.Parcel
import android.os.Parcelable


data class MovieModel (
        val title: String?,
        val description: String?,
        val rateScore: Int,
        val ageRestriction: Int,
        val imageUrl: String?,
        val id : Int,
        val genre : String
        )
