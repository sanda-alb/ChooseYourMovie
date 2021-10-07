package com.example.chooseyourmovie

import android.app.Application
import com.example.chooseyourmovie.database.MovieDatabase

class MovieApplication: Application() {

        val database by lazy { MovieDatabase.getDatabase(this) }

}

//override fun onCreate() {
//    super.onCreate()
//    val database by lazy { MovieDatabase.getDatabase(this) }
//}