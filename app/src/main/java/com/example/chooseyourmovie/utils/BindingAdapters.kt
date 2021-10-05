package com.example.chooseyourmovie.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chooseyourmovie.R

@BindingAdapter("posterUrl")
fun bindImage(imgView: ImageView, posterPath: String) {
    posterPath?.let {
        val posterUri = posterPath.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(posterUri)
//            .apply(
//                RequestOptions()
//                    .placeholder(R.drawable.loading_animation)
//                    .error(R.drawable.ic_baseline_broken_image))
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

