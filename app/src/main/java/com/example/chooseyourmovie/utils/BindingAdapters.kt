package com.example.chooseyourmovie.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.ui.overview.RemoteMovieAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@BindingAdapter("posterUrl")
fun bindImage(imgView: ImageView, posterPath: String) {
    posterPath?.let {
        val posterUri = posterPath.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(posterUri)
            .into(imgView)
    }
}

@BindingAdapter("movieGenre")
fun setGenre(txtView: TextView, genreIds: List<Int>) {
    val genreList = mutableListOf<String>()
    for (genreId in genreIds) {
        val genre = genreMap[genreId]
        genreList.add(genre!!)
    }
    txtView.text = genreList.joinToString()
}

@BindingAdapter("releaseYear")
fun setReleaseYear(txtView: TextView, releaseDate: String?) {
    val releaseYear = releaseDate?.substring(0, 4)
    txtView.text = releaseYear
}

@BindingAdapter("voteAverage")
fun doubleToString(txtView: TextView, voteAverage: Double) {
    txtView.text = voteAverage.toString()
}


//
//@BindingAdapter("listData")
//suspend fun bindRecyclerView(recyclerView: RecyclerView,
//                             data: Flow<PagingData<Movie>>
//) {
//    val adapter = recyclerView.adapter as RemoteMovieAdapter
//    adapter.submitData(data)


//private fun fetchMoviesPosters() {
//    lifecycleScope.launch {
//        remoteViewModel.fetchMoviesPosters().distinctUntilChanged().collectLatest {
//            adapter.submitData(it)
//        }
//    }
