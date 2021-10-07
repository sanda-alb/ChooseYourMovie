package com.example.chooseyourmovie.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.chooseyourmovie.models.DatabaseMovie
import com.example.chooseyourmovie.models.Movie

@Dao
interface MovieDao {

    @Query("select * from moviedatabase")
    fun getMovies(): PagingSource<Int, Movie>

    @Insert(onConflict = REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM moviedatabase")
    suspend fun clearAllMovies()

}

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remotekeys WHERE repoId = :id")
    suspend fun remoteKeysMovieId(id: Int): RemoteKeys?

    @Query("DELETE FROM remotekeys")
    suspend fun clearRemoteKeys()
}