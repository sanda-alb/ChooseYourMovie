package com.example.chooseyourmovie.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.chooseyourmovie.API_KEY
import com.example.chooseyourmovie.DEFAULT_PAGE_INDEX
import com.example.chooseyourmovie.Mapper.asDataBaseModel
import com.example.chooseyourmovie.database.MovieDatabase
import com.example.chooseyourmovie.database.RemoteKeys
import com.example.chooseyourmovie.models.Movie
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import com.example.chooseyourmovie.Mapper.asDomainModel
import com.example.chooseyourmovie.network.MovieApiService
import com.example.chooseyourmovie.MovieApplication



@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieApiService: MovieApiService,
    private val application: MovieApplication,
    private val movieDatabase: MovieDatabase = application.database
) : RemoteMediator<Int, Movie>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = movieApiService.getPopularMoviesPaged(API_KEY, page)
            val isEndOfList = response.results.isEmpty()
            movieDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    movieDatabase.getKeysDao().clearRemoteKeys()
                    movieDatabase.getMovieDao().clearAllMovies()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                movieDatabase.getKeysDao().insertAll(keys)
                movieDatabase.getMovieDao().insertAll(response.asDataBaseModel())
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, Movie>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> movieDatabase.getKeysDao().remoteKeysMovieId(movie.id)}
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, Movie>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> movieDatabase.getKeysDao().remoteKeysMovieId(movie.id)}
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, Movie>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                movieDatabase.getKeysDao().remoteKeysMovieId(repoId)
            }
        }
    }

    /**
     * this returns the page key or the final end of list success result
     */
    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, Movie>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }
}

