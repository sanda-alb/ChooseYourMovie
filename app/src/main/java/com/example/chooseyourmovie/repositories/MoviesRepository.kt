package com.example.chooseyourmovie.repositories


import androidx.paging.*
import com.example.chooseyourmovie.DEFAULT_PAGE_SIZE
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.network.MovieApi
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
        ).flow
    }

    val movies: Flow<PagingData<Movie>> = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { MoviesPagingSource(movieApiService)}
    ).flow

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}