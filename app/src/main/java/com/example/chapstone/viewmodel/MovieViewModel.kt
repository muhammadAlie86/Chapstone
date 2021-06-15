package com.example.chapstone.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor (movieUseCase: MovieUseCase): ViewModel(){

    val movie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllMovies())
}