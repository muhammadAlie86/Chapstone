package com.example.core.domain.usecase

import com.example.core.domain.model.MovieEntityModel
import com.example.core.vo.Resource
import io.reactivex.rxjava3.core.Flowable

interface MovieUseCase {

    fun getAllMovies() : Flowable<Resource<List<MovieEntityModel>>>
    fun getFavoriteMovie() : Flowable<List<MovieEntityModel>>
    fun setFavorite(movieEntityModel: MovieEntityModel, newState : Boolean)
}