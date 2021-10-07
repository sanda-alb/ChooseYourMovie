package com.example.chooseyourmovie.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chooseyourmovie.models.Movie


@Entity
data class RemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?)