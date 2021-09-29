package com.example.chooseyourmovie.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.chooseyourmovie.models.Movie

class DetailViewModel(movieItem: Movie,
                      app: Application
) : AndroidViewModel(app) {
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    init{
        _selectedMovie.value = movieItem
    }
}

class DetailViewModelFactory(
    private val movieItem: Movie,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}