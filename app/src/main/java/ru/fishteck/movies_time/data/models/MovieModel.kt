package ru.fishteck.movies_time.data.models

import android.os.Parcel
import android.os.Parcelable


data class MovieModel (
        val title: String?,
        val description: String?,
        val rateScore: Int,
        val ageRestriction: Int,
        val imageUrl: String?,
        val id : Int
        ) :  Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readInt()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(description)
                parcel.writeInt(rateScore)
                parcel.writeInt(ageRestriction)
                parcel.writeString(imageUrl)
                parcel.writeInt(id)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<MovieModel> {
                override fun createFromParcel(parcel: Parcel): MovieModel {
                        return MovieModel(parcel)
                }

                override fun newArray(size: Int): Array<MovieModel?> {
                        return arrayOfNulls(size)
                }
        }
}