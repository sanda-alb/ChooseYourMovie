package com.example.chooseyourmovie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chooseyourmovie.models.Movie


@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}





