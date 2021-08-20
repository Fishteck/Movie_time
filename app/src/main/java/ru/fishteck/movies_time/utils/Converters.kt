package ru.fishteck.movies_time.utils

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }

    @TypeConverter
    fun toListOfInt(flatStringList: String): List<Int> {
        return flatStringList.split(",").map { it.toInt() }
    }
    @TypeConverter
    fun fromListOfInt(listOfString: List<Int>): String {
        return listOfString.joinToString(",")
    }

}