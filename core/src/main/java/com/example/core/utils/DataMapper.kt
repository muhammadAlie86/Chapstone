package com.example.core.utils

import com.example.core.data.entity.local.MovieEntity
import com.example.core.data.entity.remote.Movie
import com.example.core.domain.model.MovieEntityModel


object DataMapper {

    fun mapResponseToEntity(input : List<Movie>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map{
            val movie = MovieEntity(
                    it.id,
                    it.backdropImg,
                    it.title,
                    it.imgPath,
                    it.language,
                    it.overview,
                    it.releaseDate,
                    it.voteAverage,
                    it.isFavorite

            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapEntitiesToDomain(input: List<MovieEntity>): List<MovieEntityModel> =
        input.map {
            MovieEntityModel(
                    it.id,
                    it.backdropImg,
                    it.title,
                    it.imgPath,
                    it.language,
                    it.overview,
                    it.releaseDate,
                    it.voteAverage,
                    it.isFavorite
            )
        }


    fun mapDomainToEntity(input: MovieEntityModel) : MovieEntity =
        MovieEntity(
                input.id,
                input.backdropImg,
                input.title,
                input.imgPath,
                input.language,
                input.overview,
                input.releaseDate,
                input.voteAverage,
                input.isFavorite

            )

}

