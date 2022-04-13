package com.mediacorp.moviedb.utils

object AppUtilsConstant {
    const val API_KEY = "ed315a941ea85ecaa3b74d18450bfa2a"
    const val API_URL = "https://api.themoviedb.org/3/"
    const val POSTER_PATH: String = "https://image.tmdb.org/t/p/w500"

    private const val API_MOVIE_PATH = "movie"
    const val POPULAR_MOVIE_API_ENDPOINT = "$API_MOVIE_PATH/popular"
    const val UPCOMING_MOVIE_API_ENDPOINT = "$API_MOVIE_PATH/upcoming"
    const val QUERY_PAGE_PARAM = "page"
    const val API_KEY_REQUEST_PARAM = "api_key"
    const val API_SESSION_TIMEOUT = 15L
    const val SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz"
    const val UTC_TIMEZONE = "UTC"
}
