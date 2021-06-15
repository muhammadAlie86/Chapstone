package com.example.favorite.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val listFavoriteMovie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getFavoriteMovie())
}