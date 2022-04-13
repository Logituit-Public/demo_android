package com.mediacorp.moviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mediacorp.moviedb.data.api.ApiServiceImpl
import com.mediacorp.moviedb.data.repository.MovieRepository
import com.mediacorp.moviedb.data.repository.mapper.MovieDetailItemMapper

class ViewModelFactory(
    private val apiServiceImpl: ApiServiceImpl,
    private val movieDetailItemMapper: MovieDetailItemMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(apiServiceImpl, movieDetailItemMapper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
