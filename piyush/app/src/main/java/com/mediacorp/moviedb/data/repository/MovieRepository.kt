package com.mediacorp.moviedb.data.repository

import com.mediacorp.moviedb.data.api.ApiServiceImpl
import com.mediacorp.moviedb.data.repository.mapper.MovieDetailItemMapper
import com.mediacorp.moviedb.datamodel.MovieDetailViewItem
import io.reactivex.Observable
import io.reactivex.Single

class MovieRepository(
    private val apiServiceImpl: ApiServiceImpl,
    private val itemMapper: MovieDetailItemMapper
) {
    fun getPopularMovies(pageNo: Int): Single<MovieDetailViewItem?> =
        apiServiceImpl.getPopularMovies(pageNo).map { itemMapper.mapFrom(it) }

    fun getUpComingMovies(pageNo: Int): Single<MovieDetailViewItem?> =
        apiServiceImpl.getUpComingMovies(pageNo).map { itemMapper.mapFrom(it) }
}