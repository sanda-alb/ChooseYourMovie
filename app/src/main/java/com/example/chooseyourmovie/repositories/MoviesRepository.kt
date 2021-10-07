package com.example.chooseyourmovie.repositories


import androidx.paging.*
import com.example.chooseyourmovie.DEFAULT_PAGE_SIZE
import com.example.chooseyourmovie.MovieApplication
import com.example.chooseyourmovie.database.MovieDatabase
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.network.MovieApi
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class MoviesRepository{


    companion object {

        fun getInstance() = MoviesRepository()
    }

    private val movieApiService = MovieApi.retrofitService
    private val movieDatabase: MovieDatabase
        get() {
            TODO()
        }

    fun letMoviesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviesPagingSource(movieApiService) }
        ).flow
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }


//    fun letMoviesFlowDb(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Movie>> {
//        if (movieDatabase == null) throw IllegalStateException("Database is not initialized")
//
//        val pagingSourceFactory = { movieDatabase.getMovieDao().getMovies() }
//        return Pager(
//            config = pagingConfig,
//            pagingSourceFactory = pagingSourceFactory,
//            remoteMediator = MovieRemoteMediator(movieApiService, MovieApplication(), movieDatabase)
//        ).flow
//    }
}