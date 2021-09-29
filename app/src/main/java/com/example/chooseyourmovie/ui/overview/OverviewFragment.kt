package com.example.chooseyourmovie.ui.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.example.chooseyourmovie.R
import com.example.chooseyourmovie.databinding.OverviewFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class OverviewFragment : Fragment() {

    private var adapter = RemoteMovieAdapter()
    lateinit var recyclerView: RecyclerView

    @ExperimentalPagingApi
    private val remoteViewModel: RemoteViewModel by lazy {
        ViewModelProvider(this).get(RemoteViewModel::class.java)
    }

    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        fetchMoviesPosters()
        binding.moviesGrid.adapter = adapter

        return binding.root
    }


    private fun fetchMoviesPosters() {
        lifecycleScope.launch {
            remoteViewModel.fetchMoviesPosters().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }


}
