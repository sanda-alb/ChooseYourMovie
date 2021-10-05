package com.example.chooseyourmovie.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(value: List<Int>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun fromStringToList(string: String): List<Int> {
        return string.split(", ").toList().map { it.toInt() }
    }
}