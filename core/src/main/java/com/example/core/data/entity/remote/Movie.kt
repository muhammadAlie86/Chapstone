package com.example.core.data.entity.remote

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    val id : Int? ,

    @SerializedName("backdrop_path")
    val backdropImg : String?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("poster_path")
    val imgPath : String?,

    @SerializedName("original_language")
    val language : String?,

    @SerializedName("overview")
    val overview : String?,

    @SerializedName("release_date")
    val releaseDate : String?,

    @SerializedName("vote_average")
    val voteAverage : String?,

    var isFavorite : Boolean = false

    )
