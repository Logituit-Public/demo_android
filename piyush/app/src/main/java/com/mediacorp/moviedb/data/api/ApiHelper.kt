package com.mediacorp.moviedb.data.api

import com.mediacorp.moviedb.datamodel.MovieListResponse
import io.reactivex.Single

interface ApiHelper {
    /*
    * to get the Popular movie response
    * */
    fun getPopularMovies(page: Int): Single<MovieListResponse>

    /*
    * to get the Upcoming movie response
    * */
    fun getUpComingMovies(page: Int): Single<MovieListResponse>
}