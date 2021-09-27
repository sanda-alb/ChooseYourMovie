//package com.example.chooseyourmovie.repositories
//
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import com.example.chooseyourmovie.NETWORK_PAGE_SIZE
//import com.example.chooseyourmovie.models.Movie
//import com.example.mymovieapp.network.MovieApiService
//import kotlinx.coroutines.flow.Flow
//
//
//interface MoviesRemoteDataSource {
//
//    suspend fun getMoviesFlow(): Either<Failure, List<Movie>>
//}
//
//internal class MoviesRemoteDataSourceImpl(
//    private val movieService: MovieApiService
//) : MoviesRemoteDataSource  {
//
//    override fun getMoviesFlow(): Flow<PagingData<Movie>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = NETWORK_PAGE_SIZE,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                MoviesPagingSource(movieService)
//            }
//        ).flow
//    }
//}