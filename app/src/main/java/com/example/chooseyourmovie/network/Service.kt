package com.example.mymovieapp.network

import com.example.chooseyourmovie.models.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMoviesPaged(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int)
    : MovieApiResponse

}
