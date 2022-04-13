package com.mediacorp.moviedb.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieViewItem(
    val id: Int,
    val imagePath: String,
    val title: String,
    val overview: String,
    val releaseDate: String
) : Parcelable
