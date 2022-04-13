package com.mediacorp.moviedb.data.repository.mapper

import com.mediacorp.moviedb.datamodel.MovieDetailViewItem
import com.mediacorp.moviedb.datamodel.MovieListResponse
import com.mediacorp.moviedb.datamodel.MovieViewItem
import com.mediacorp.moviedb.utils.AppUtilsConstant

/**
 * Class used to create list of MovieViewItem objects
 * */
class MovieDetailItemMapper() :
    Mapper<MovieListResponse, MovieDetailViewItem?> {
    override fun mapFrom(item: MovieListResponse): MovieDetailViewItem? {
        return MovieDetailViewItem(
            movies = item.results?.map { movie ->
                MovieViewItem(
                    id = movie.id ?: 0,
                    imagePath = "${AppUtilsConstant.POSTER_PATH}${movie.posterPath}",
                    title = movie.title.orEmpty(),
                    overview = movie.overview.orEmpty(),
                    releaseDate = movie.releaseDate.orEmpty(),
                )
            }.orEmpty()
        )
    }
}