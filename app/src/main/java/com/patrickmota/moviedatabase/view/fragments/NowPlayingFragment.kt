package com.patrickmota.moviedatabase.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrickmota.moviedatabase.databinding.FragmentMainBinding
import com.patrickmota.moviedatabase.view.activities.MovieDetailActivity
import com.patrickmota.moviedatabase.view.adapters.MovieAdapter
import com.patrickmota.moviedatabase.viewmodel.activities.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NowPlayingFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentMainBinding

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

    }

    private fun initUi() {
        mainViewModel.getNowPlayingMovie()
        mainViewModel.movieResponse().observe(viewLifecycleOwner) {
            val recyclerView: RecyclerView = binding.recyclerview

            movieAdapter = MovieAdapter { movie ->
                startActivity(
                    Intent(context, MovieDetailActivity::class.java)
                        .putExtra("movieId", movie.id)
                )
            }

            movieAdapter.setDataSet(it.results)

            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = movieAdapter
            }
        }

        mainViewModel.error().observe(viewLifecycleOwner) {
            showError()
        }
    }

    private fun showError() {
        binding.ivError.visibility = View.VISIBLE
        binding.tvError.visibility = View.VISIBLE
        binding.tvErrorTip.visibility = View.VISIBLE
    }

}