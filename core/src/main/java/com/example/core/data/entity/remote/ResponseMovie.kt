package com.example.core.data.entity.remote

import com.google.gson.annotations.SerializedName

data class ResponseMovie (

    @SerializedName("results")
    val result : List<Movie>
)