package com.example.core.data.database.dao

import androidx.room.*
import com.example.core.data.entity.local.MovieEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_entities ORDER BY movieId ASC")
    fun getAllMovies() : Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId" )
    fun getMovieById(movieId : Int) : Flowable<MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE favorite = 1")
    fun getFavoriteMovies() : Flowable<List<MovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie (movie : List<MovieEntity>) : Completable

    @Update
    fun updateMovie(movieEntity: MovieEntity)




}