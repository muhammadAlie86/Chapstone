package com.example.chapstone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.MovieEntityModel
import com.example.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel(){

    fun setFavoriteMovie(movieEntityModel: MovieEntityModel, newStatus:Boolean) =
            movieUseCase.setFavorite(movieEntityModel, newStatus)

}