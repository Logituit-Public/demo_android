package com.mediacorp.moviedb.viewmodel

import androidx.lifecycle.ViewModel
import com.mediacorp.moviedb.data.repository.FavouriteMovieRepository
import io.reactivex.disposables.CompositeDisposable

class FavouriteMovieViewModel(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addMovieToFavourite(movieId: String, isFavourite: Boolean) {
        favouriteMovieRepository.saveFavouriteMovieId(movieId, isFavourite)
    }

    fun isMovieAddedToFavourite(movieId: String): Boolean {
        return favouriteMovieRepository.isAddedToFavourite(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}