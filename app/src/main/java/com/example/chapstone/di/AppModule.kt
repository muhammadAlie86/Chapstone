package com.example.chapstone.di

import com.example.core.domain.usecase.MovieIterator
import com.example.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieIterator: MovieIterator) : MovieUseCase
}