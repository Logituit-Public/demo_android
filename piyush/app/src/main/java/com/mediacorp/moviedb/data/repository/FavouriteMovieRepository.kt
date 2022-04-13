package com.mediacorp.moviedb.data.repository

import android.content.SharedPreferences

class FavouriteMovieRepository(
    private val sharedPreferences: SharedPreferences
) {

    fun isAddedToFavourite(movieId: String): Boolean {
        return sharedPreferences.getBoolean(movieId, false)
    }

    fun saveFavouriteMovieId(movieId: String, isFavourite: Boolean) {
        sharedPreferences.edit().putBoolean(movieId, isFavourite).apply()
    }
}
