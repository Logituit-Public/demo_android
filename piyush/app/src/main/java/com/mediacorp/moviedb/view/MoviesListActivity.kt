package com.mediacorp.moviedb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mediacorp.moviedb.databinding.ActivityMovieListBinding

class MoviesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
