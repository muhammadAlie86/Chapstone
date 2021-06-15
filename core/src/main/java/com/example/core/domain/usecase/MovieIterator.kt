package com.example.core.domain.usecase

import com.example.core.domain.model.MovieEntityModel
import com.example.core.domain.repository.IMovieRepository
import com.example.core.vo.Resource
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class MovieIterator @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovies(): Flowable<Resource<List<MovieEntityModel>>> =
            movieRepository.getAllMovies()

    override fun getFavoriteMovie(): Flowable<List<MovieEntityModel>> =
            movieRepository.getFavoriteMovie()

    override fun setFavorite(movieEntityModel: MovieEntityModel, newState: Boolean) =
            movieRepository.setFavorite(movieEntityModel,newState)


}