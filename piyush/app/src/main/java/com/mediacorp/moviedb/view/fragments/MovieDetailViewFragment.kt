package com.mediacorp.moviedb.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mediacorp.moviedb.R
import com.mediacorp.moviedb.databinding.FragmentMovieDetailViewBinding
import com.mediacorp.moviedb.datamodel.MovieViewItem
import com.mediacorp.moviedb.viewmodel.FavouriteMovieViewModel
import com.mediacorp.moviedb.viewmodel.FavouriteMovieViewModelFactory


class MovieDetailViewFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMovieDetailViewBinding

    private val favouriteMovieViewModel: FavouriteMovieViewModel by viewModels {
        FavouriteMovieViewModelFactory(
            requireActivity().getSharedPreferences(
                FAVOURITE_MOVIE_SHARED_PREFS, Context.MODE_PRIVATE
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDetails: MovieViewItem? = arguments?.getParcelable(MOVIE_DETAILS)

        binding.apply {

            movieDetails?.let { movieDetails ->
                txtMovieTitle.text = movieDetails.title
                txtMovieOverview.text = movieDetails.overview
                txtMovieReleaseDate.text = movieDetails.releaseDate
                Glide.with(imgMovieThumbnail.context)
                    .load(movieDetails.imagePath)
                    .into(imgMovieThumbnail)

                // get favourite movie status and update the drawable
                updateFavouriteDrawable(favouriteMovieViewModel.isMovieAddedToFavourite("" + movieDetails.id))

                imgAddMovieToFavourite.setOnClickListener {
                    val isFavourite = favouriteMovieViewModel.isMovieAddedToFavourite("" + it.id)
                    favouriteMovieViewModel.addMovieToFavourite(
                        "" + it.id,
                        !isFavourite
                    )
                    updateFavouriteDrawable(favouriteMovieViewModel.isMovieAddedToFavourite("" + it.id))
                }
            }
        }
    }

    /*
    * update favourite icon based on the movie favourite status
    * */
    private fun updateFavouriteDrawable(isFavouriteMovie: Boolean) {
        binding.imgAddMovieToFavourite.apply {
            if (isFavouriteMovie) {
                this.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_start_favourite_movie
                    )
                )
            } else {
                this.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_non_favourite_movie_star
                    )
                )
            }
        }
    }

    companion object {
        const val MOVIE_DETAILS = "movie_details"
        const val FAVOURITE_MOVIE_SHARED_PREFS = "favourite_movie_prefs"
        fun with(
            movieViewItem: MovieViewItem
        ): Bundle {
            return Bundle().apply {
                putParcelable(MOVIE_DETAILS, movieViewItem)
            }
        }
    }
}
