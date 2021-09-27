package com.example.chooseyourmovie.repositories

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.chooseyourmovie.DEFAULT_PAGE_SIZE
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.network.MovieApi
import com.example.mymovieapp.network.MovieApiService
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class MoviesRepository {

    companion object {

        fun getInstance() = MoviesRepository()
    }

    private val movieApiService = MovieApi.retrofitService

    fun letMoviesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviesPagingSource(movieApiService) }
        ).flow//TODO
    }

    //for live data users
    fun letMoviesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviesPagingSource(movieApiService)}
        ).liveData
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}