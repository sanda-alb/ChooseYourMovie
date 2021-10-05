//package com.example.chooseyourmovie.repositories
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import com.bumptech.glide.load.HttpException
//import com.example.chooseyourmovie.API_KEY
//import com.example.chooseyourmovie.database.MovieDatabase
//import com.example.chooseyourmovie.models.DatabaseMovie
//import com.example.mymovieapp.network.MovieApiService
//import java.io.IOException
//
//@OptIn(ExperimentalPagingApi::class)
//class MovieDBRemoteMediator(
//    private val movieDBService: MovieApiService,
//    private val movieDatabase: MovieDatabase
//) : RemoteMediator<Int, DatabaseMovie>() {
//    override suspend fun load(
//        // 1
//        loadType: LoadType,
//        // 2
//        state: PagingState<Int, DatabaseMovie>
//    ): MediatorResult {
//        return try {
//            // 1
//            val response = movieDBService.getPopularMoviesPaged(API_KEY, )
//
//            // 2
//            val listing = response.body()?.data
//            val redditPosts = listing?.children?.map { it.data }
//
//            // 3
//            if (redditPosts != null) {
//                movieDatabase.redditPostsDao().savePosts(redditPosts)
//            }
//
//            // 4
//            MediatorResult.Success(endOfPaginationReached = listing?.after == null)
//
//            // 5
//        } catch (exception: IOException) {
//            MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            MediatorResult.Error(exception)
//        }
//    }
//}
