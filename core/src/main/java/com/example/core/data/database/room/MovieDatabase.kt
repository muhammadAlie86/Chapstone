package com.example.core.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.database.dao.MovieDao
import com.example.core.data.entity.local.MovieEntity

@Database(entities = [MovieEntity::class],version = 1,exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}