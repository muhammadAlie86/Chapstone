@file:Suppress("DEPRECATED_ANNOTATION")

package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntityModel(
    val id : Int? ,

    val backdropImg : String?,

    val title : String?,

    val imgPath : String?,

    val language : String?,

    val overview : String?,

    val releaseDate : String?,

    val voteAverage : String?,

    var isFavorite : Boolean = false

) : Parcelable