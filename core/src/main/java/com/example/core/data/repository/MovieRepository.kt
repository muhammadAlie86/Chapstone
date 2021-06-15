package com.example.core.data.repository

import com.example.core.data.entity.remote.Movie
import com.example.core.data.source.LocalDataSource
import com.example.core.data.source.NetworkBoundResource
import com.example.core.data.source.RemoteDataSource
import com.example.core.domain.model.MovieEntityModel
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutor
import com.example.core.utils.DataMapper
import com.example.core.vo.ApiResponse
import com.example.core.vo.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : IMovieRepository {


    override fun getAllMovies(): Flowable<Resource<List<MovieEntityModel>>> =
        object : NetworkBoundResource<List<MovieEntityModel>, List<Movie>>(appExecutor) {
            override fun loadFromDB(): Flowable<List<MovieEntityModel>> =
                localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }


            override fun shouldFetch(data: List<MovieEntityModel>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<Movie>>> =
                    remoteDataSource.getMovie()


            override fun saveCallResult(data: List<Movie>) {
                val movieList = DataMapper.mapResponseToEntity(data)
                localDataSource.insertMovie(movieList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()

            }
        }.asFlowable()

    override fun getFavoriteMovie(): Flowable<List<MovieEntityModel>> =
            localDataSource.getFavoriteMovies().map {
                DataMapper.mapEntitiesToDomain(it)
            }


    override fun setFavorite(movieEntityModel: MovieEntityModel, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movieEntityModel)
        appExecutor.diskIO().execute{localDataSource.setFavorite(movieEntity,newState)}
    }
}