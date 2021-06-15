package com.example.core.data.api

import com.example.core.BuildConfig
import com.example.core.data.api.Constant.Companion.LANGUAGE
import com.example.core.data.api.Constant.Companion.PAGE
import com.example.core.data.entity.remote.ResponseMovie
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/now_playing?")
    fun getMovie (
        @Query ("api_key") api_key : String = BuildConfig.API_KEY,
        @Query("language") language : String = LANGUAGE,
        @Query("page") page : Int = PAGE
    ): Flowable<ResponseMovie>


}