package com.example.chooseyourmovie.models

import com.squareup.moshi.JsonClass

data class MovieApiResponse (
    val page: Int,
    val results: List<MovieNetwork>
)