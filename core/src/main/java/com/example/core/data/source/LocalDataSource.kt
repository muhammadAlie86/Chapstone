package com.example.core.data.source

import com.example.core.data.database.dao.MovieDao
import com.example.core.data.entity.local.MovieEntity
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {


    fun getAllMovies(): Flowable<List<MovieEntity>> =
            movieDao.getAllMovies()

    fun getFavoriteMovies(): Flowable<List<MovieEntity>> =
            movieDao.getFavoriteMovies()

    fun insertMovie(movieEntity: List<MovieEntity>) =
            movieDao.insertMovie(movieEntity)

    fun setFavorite(movieEntity: MovieEntity, newState : Boolean){
        movieEntity.isFavorite = newState
        movieDao.updateMovie(movieEntity)
    }

}