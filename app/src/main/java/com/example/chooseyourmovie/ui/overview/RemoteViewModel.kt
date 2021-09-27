package com.example.chooseyourmovie.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RemoteViewModel(
    private val repository: MoviesRepository = MoviesRepository.getInstance()
) : ViewModel() {

    /**
     * we just mapped the data received from the repository to [PagingData<String>] to show the map
     * function you can always return the original model if needed, in our case it would be [Movie]
     */

    fun fetchMoviesPosters(): Flow<PagingData<Movie>> {
        return repository.letMoviesFlow()
//            .cachedIn(viewModelScope)
    }


}

