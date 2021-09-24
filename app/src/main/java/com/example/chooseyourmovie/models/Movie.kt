package com.example.chooseyourmovie.models

import android.os.Parcelable
import com.example.chooseyourmovie.BASE_POSTER_URL
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class Movie(

    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    var posterLink: String = BASE_POSTER_URL+ posterPath)


