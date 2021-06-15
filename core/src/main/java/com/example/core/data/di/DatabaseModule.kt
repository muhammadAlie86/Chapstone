package com.example.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.database.dao.MovieDao
import com.example.core.data.database.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDB(context: Context ): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}