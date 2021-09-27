package com.example.chooseyourmovie.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chooseyourmovie.databinding.MovieGridBinding
import com.example.chooseyourmovie.models.Movie

class PosterGridAdapter() : ListAdapter<Movie,
        PosterGridAdapter.MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PosterGridAdapter.MovieViewHolder {
        return MovieViewHolder(
            MovieGridBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }



    override fun onBindViewHolder(holder: PosterGridAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
//        holder.itemView.setOnClickListener{
//            onClickListener.onClick(Movie)
//        }
        holder.bind(movie)
    }


    class MovieViewHolder(private var binding: MovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

//    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
//        fun onClick(movie: Movie) = clickListener(movie)
//    }

}