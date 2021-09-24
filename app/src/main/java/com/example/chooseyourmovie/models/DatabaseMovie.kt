package com.example.chooseyourmovie.models

import com.example.chooseyourmovie.BASE_POSTER_URL

data class DatabaseMovie(

    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    var posterLink: String = BASE_POSTER_URL + posterPath)


