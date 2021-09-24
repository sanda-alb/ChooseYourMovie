package com.example.chooseyourmovie.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.ui.overview.PosterGridAdapter

@BindingAdapter("posterUrl")
fun bindImage(imgView: ImageView, posterPath: String) {
    posterPath?.let {
        val posterUri = posterPath.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(posterUri)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Movie>?) {
    val adapter = recyclerView.adapter as PosterGridAdapter
    adapter.submitList(data)
}