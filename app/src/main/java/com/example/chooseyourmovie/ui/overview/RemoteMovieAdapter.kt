package com.example.chooseyourmovie.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chooseyourmovie.databinding.MovieGridBinding
import com.example.chooseyourmovie.models.Movie


val USER_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}

class RemoteMovieAdapter(private val onClickListener: OnClickListener) : PagingDataAdapter<Movie,
        RemoteMovieAdapter.MoviePosterViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )
            : MoviePosterViewHolder {
        return MoviePosterViewHolder(
            MovieGridBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    inner class MoviePosterViewHolder(private val binding: MovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener{
            if (movie != null) {
                onClickListener.onClick(movie)
            }
        }
        if (movie != null) {
            holder.bind(movie)
        }
//        getItem(position)?.let { holder.bind(it) }

    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }


}
