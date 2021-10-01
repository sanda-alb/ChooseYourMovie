package com.example.chooseyourmovie.ui.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.example.chooseyourmovie.databinding.OverviewFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class OverviewFragment : Fragment() {

    val viewModel: RemoteViewModel by lazy {
        ViewModelProvider(this).get(RemoteViewModel::class.java)
    }


    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        binding.moviesGrid.adapter = RemoteMovieAdapter(RemoteMovieAdapter.OnClickListener{
            viewModel.displayMovieDetails(it)
        })
        
        binding.viewModel = viewModel

        val adapter = binding.moviesGrid.adapter as RemoteMovieAdapter

        lifecycleScope.launch {
            viewModel.fetchMoviesPosters().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }

        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}
