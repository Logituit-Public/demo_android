package com.mediacorp.moviedb.data.api

import com.mediacorp.moviedb.datamodel.MovieListResponse
import com.mediacorp.moviedb.utils.AppUtilsConstant
import com.mediacorp.moviedb.utils.AppUtilsConstant.POPULAR_MOVIE_API_ENDPOINT
import com.mediacorp.moviedb.utils.AppUtilsConstant.UPCOMING_MOVIE_API_ENDPOINT
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /*
    *  Make an API call [GET Method] to to fetch Popular Movie Response
    * */
    @GET(POPULAR_MOVIE_API_ENDPOINT)
    fun getPopularMovies(@Query(AppUtilsConstant.QUERY_PAGE_PARAM) page: Int): Single<MovieListResponse>

    /*
    * Make an API call [GET Method] to to fetch Upcoming Movie Response
    * */
    @GET(UPCOMING_MOVIE_API_ENDPOINT)
    fun getUpComingMovies(@Query(AppUtilsConstant.QUERY_PAGE_PARAM) page: Int): Single<MovieListResponse>
}
