package com.mediacorp.moviedb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mediacorp.moviedb.data.repository.MovieRepository
import com.mediacorp.moviedb.datamodel.MovieDetailViewItem
import com.mediacorp.moviedb.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovieListObservable = MutableLiveData<Resource<MovieDetailViewItem>>()
    val popularMovieListResponse: MutableLiveData<Resource<MovieDetailViewItem>> = _popularMovieListObservable

    private val _upcomingMovieListResponse = MutableLiveData<Resource<MovieDetailViewItem>>()
    val upcomingMovieListResponse: MutableLiveData<Resource<MovieDetailViewItem>> = _upcomingMovieListResponse

    private val compositeDisposable = CompositeDisposable()

    /**
     * call popular movie request and post response
     * */
    fun getPopularMovies(pageNo: Int) {
        _popularMovieListObservable.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieRepository.getPopularMovies(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ moviesList ->
                    Log.d("API_RESPONSE", "getPopularMovies moviesList: $moviesList")
                    _popularMovieListObservable.postValue(Resource.success(moviesList))
                }, {
                    _popularMovieListObservable.postValue(Resource.error(it.localizedMessage, null))
                })
        )
    }

    /**
     * call upcoming movie request and post response
     * */
    fun getUpcomingMovies(pageNo: Int) {
        _upcomingMovieListResponse.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieRepository.getUpComingMovies(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ moviesList ->
                    Log.d("API_RESPONSE", "getUpcomingMovies moviesList: $moviesList")
                    _upcomingMovieListResponse.postValue(Resource.success(moviesList))
                }, {
                    _upcomingMovieListResponse.postValue(Resource.error(it.localizedMessage, null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}