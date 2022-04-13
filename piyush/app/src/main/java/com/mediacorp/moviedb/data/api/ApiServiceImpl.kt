package com.mediacorp.moviedb.data.api

/**
 * Provide an API call Implementation
 * */
class ApiServiceImpl(private val apiCallFactory: ApiCallFactory) : ApiHelper {
    override fun getPopularMovies(page: Int) = apiCallFactory.provideApi().getPopularMovies(page)
    override fun getUpComingMovies(page: Int) = apiCallFactory.provideApi().getUpComingMovies(page)
}