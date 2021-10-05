package com.example.chooseyourmovie.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chooseyourmovie.BASE_POSTER_URL




@Entity
data class RemoteKeys(
    @PrimaryKey val repoId: String,
    val prevKey: Int?,
    val nextKey: Int?)