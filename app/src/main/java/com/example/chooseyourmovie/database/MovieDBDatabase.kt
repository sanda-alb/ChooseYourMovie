package com.example.chooseyourmovie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chooseyourmovie.models.DatabaseMovie


@Database(
    entities=[DatabaseMovie::class],
    version = 1,
    exportSchema = false)

@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    companion object {
        fun create(context: Context): MovieDatabase{
            val databaseBuilder =
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            return databaseBuilder.build()
        }
    }


    abstract fun movieDao(): MovieDao
}


