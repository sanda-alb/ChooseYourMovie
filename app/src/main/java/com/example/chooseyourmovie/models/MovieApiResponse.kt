package com.example.chooseyourmovie.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieApiResponse (
    val page: Int,
    val results: List<MovieNetwork>
)