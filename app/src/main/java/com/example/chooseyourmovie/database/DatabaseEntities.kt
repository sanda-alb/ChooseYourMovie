package com.example.chooseyourmovie.database

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "remotekeys")
data class RemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?)