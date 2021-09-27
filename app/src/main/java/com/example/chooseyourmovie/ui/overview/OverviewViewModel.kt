package com.example.chooseyourmovie.ui.overview

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.chooseyourmovie.API_KEY
import com.example.chooseyourmovie.Mapper.asDomainModel
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.network.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movieList = MovieApi.retrofitService.getPopularMovies(API_KEY)
                _movieList.postValue(movieList.asDomainModel() )
            } catch (e: Exception) {
                e.printStackTrace()
                _movieList.value = ArrayList()
            }
        }
    }

}