package com.mediacorp.moviedb.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mediacorp.moviedb.data.repository.FavouriteMovieRepository

class FavouriteMovieViewModelFactory(
    private val sharedPreferences: SharedPreferences?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteMovieViewModel::class.java)) {
            return sharedPreferences?.let { FavouriteMovieRepository(it) }?.let { FavouriteMovieViewModel(it) } as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}