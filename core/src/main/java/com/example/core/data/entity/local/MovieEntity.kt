package com.example.core.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity (

    @ColumnInfo(name = "movieId")
    @PrimaryKey(autoGenerate = false)
    val id : Int? ,

    @ColumnInfo(name = "backdrop_path")
    val backdropImg : String?,

    @ColumnInfo(name = "title")
    val title : String?,

    @ColumnInfo(name = "poster_path")
    val imgPath : String?,

    @ColumnInfo(name = "original_language")
    val language : String?,

    @ColumnInfo(name = "overview")
    val overview : String?,

    @ColumnInfo(name = "release_date")
    val releaseDate : String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage : String?,

    @ColumnInfo(name = "favorite")
    var isFavorite : Boolean = false

)