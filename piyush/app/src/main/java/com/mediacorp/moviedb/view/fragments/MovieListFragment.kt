package com.mediacorp.moviedb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediacorp.moviedb.data.api.ApiCallFactory
import com.mediacorp.moviedb.data.api.ApiServiceImpl
import com.mediacorp.moviedb.data.repository.mapper.MovieDetailItemMapper
import com.mediacorp.moviedb.databinding.FragmentMovieListBinding
import com.mediacorp.moviedb.utils.Status
import com.mediacorp.moviedb.view.adapter.MovieAdapter
import com.mediacorp.moviedb.viewmodel.MovieViewModel
import com.mediacorp.moviedb.viewmodel.ViewModelFactory

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var popularMovieAdapter: MovieAdapter
    private lateinit var upcomingMovieAdapter: MovieAdapter
    private var upcomingMoviePageCount = 1
    private var popularMoviePageCount = 1

    private val movieViewModel: MovieViewModel by viewModels {
        ViewModelFactory(ApiServiceImpl(ApiCallFactory), MovieDetailItemMapper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListUI()
        setupPopularMovieObserver()
        setupUpcomingMovieObserver()

        // hit api call to get the movie list
        movieViewModel.getPopularMovies(popularMoviePageCount)
        movieViewModel.getUpcomingMovies(upcomingMoviePageCount)
    }

    private fun setupListUI() {
        // set up popular movie adapter
        binding.recViewPopularMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        popularMovieAdapter = MovieAdapter(arrayListOf(), requireActivity().supportFragmentManager)
        // popularMovieItemAdapter = MovieItemAdapter(arrayListOf(), it)

        binding.recViewPopularMovies.adapter = popularMovieAdapter

        // set up upcoming movie adapter
        binding.recViewUpcomingMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        upcomingMovieAdapter = MovieAdapter(arrayListOf(), requireActivity().supportFragmentManager)
        binding.recViewUpcomingMovies.adapter = upcomingMovieAdapter
    }

    /**
     * Observe Popular Movie API response and show to the list
     * */
    private fun setupPopularMovieObserver() {
        movieViewModel.popularMovieListResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movieDetails ->
                        popularMovieAdapter.addData(movieDetails.movies)
                        popularMovieAdapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * Observe Upcoming Movie API response and show to the list
     * */
    private fun setupUpcomingMovieObserver() {
        movieViewModel.upcomingMovieListResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movieDetails ->
                        upcomingMovieAdapter.clearData()
                        upcomingMovieAdapter.addData(movieDetails.movies)
                        upcomingMovieAdapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
