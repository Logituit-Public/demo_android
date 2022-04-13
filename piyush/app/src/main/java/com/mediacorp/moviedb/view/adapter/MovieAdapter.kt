package com.mediacorp.moviedb.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mediacorp.moviedb.databinding.MovieItemLayoutBinding
import com.mediacorp.moviedb.datamodel.MovieViewItem
import com.mediacorp.moviedb.view.fragments.MovieDetailViewFragment

class MovieAdapter(
    private val movieList: ArrayList<MovieViewItem>,
    private val fragmentManager: FragmentManager,
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    // private var onMovieItemClickListener: OnMovieItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemBinding = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieViewItem: MovieViewItem = movieList[position]
        holder.bind(movieViewItem, fragmentManager)
    }

    class MovieHolder(private val itemBinding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(
            itemBinding
                .root
        ) {
        fun bind(movieViewItem: MovieViewItem, fragmentManager: FragmentManager) {
            itemBinding.txtMovieTitle.text = movieViewItem.title
            Glide.with(itemBinding.imgMovieThumbnail.context)
                .load(movieViewItem.imagePath)
                .into(itemBinding.imgMovieThumbnail)

            itemBinding.movieThumbnailCardView.setOnClickListener {
                Log.d(
                    "ON_CLICK", "clicked on the movieDetails id: ${movieViewItem.id} " +
                            " title: ${movieViewItem.title}"
                )

                val bottomSheetDialogFragment = MovieDetailViewFragment()
                bottomSheetDialogFragment.arguments = MovieDetailViewFragment.with(movieViewItem)
                bottomSheetDialogFragment.show(fragmentManager, "")
            }
        }
    }

    fun addData(list: List<MovieViewItem>) {
        movieList.addAll(list)
    }

    fun clearData() {
        movieList.clear()
    }

    override fun getItemCount(): Int = movieList.size
}