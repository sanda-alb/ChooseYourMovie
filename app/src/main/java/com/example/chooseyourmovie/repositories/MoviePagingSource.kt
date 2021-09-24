package com.example.chooseyourmovie.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chooseyourmovie.API_KEY
import com.example.chooseyourmovie.DEFAULT_PAGE_INDEX
import com.example.chooseyourmovie.Mapper.asDomainModel
import com.example.chooseyourmovie.models.Movie
import com.example.mymovieapp.network.MovieApiService
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val myRetrofitService: MovieApiService
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        // Retrofit calls that return the body type throw either IOException for network
        // failures, or HttpException for any non-2xx HTTP status codes. This code reports all
        // errors to the UI, but you can inspect/wrap the exceptions to provide more context.
        return try {
            // Key may be null during a refresh, if no explicit key is passed into Pager
            // construction. Use 0 as default, because our API is indexed started at index 0
            val pageNumber = params.key ?: DEFAULT_PAGE_INDEX

            // Suspending network load via Retrofit. This doesn't need to be wrapped in a
            // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine
            // CallAdapter dispatches on a worker thread.
            val response = myRetrofitService.getPopularMoviesPaged(API_KEY, pageNumber)
            val moviesList = response.asDomainModel()
            // Since 0 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (pageNumber == DEFAULT_PAGE_INDEX) null else pageNumber - 1


            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded

            val nextKey = if (response.results.isNotEmpty())  pageNumber + 1 else null
            LoadResult.Page(
                data = moviesList,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}