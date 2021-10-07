package com.example.chooseyourmovie.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chooseyourmovie.BASE_POSTER_URL
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Movie(

    @PrimaryKey
    val id: Int,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double,
    var posterLink: String = BASE_POSTER_URL+ posterPath) : Parcelable


