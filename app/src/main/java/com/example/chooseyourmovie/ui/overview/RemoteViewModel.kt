package com.example.chooseyourmovie.ui.overview

import androidx.lifecycle.*
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

    private val _navigateToSelectedMovie= MutableLiveData<Movie?>()
    val navigateToSelectedMovie: LiveData<Movie?>
        get() = _navigateToSelectedMovie

    /**
     * we just mapped the data received from the repository to [PagingData<String>] to show the map
     * function you can always return the original model if needed, in our case it would be [Movie]
     */

    fun fetchMoviesPosters(): Flow<PagingData<Movie>> {
        return repository.letMoviesFlow().cachedIn(viewModelScope)
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }
    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }


}

