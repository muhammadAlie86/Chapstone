package com.example.core.data.source

import com.example.core.data.api.ApiClient
import com.example.core.data.entity.remote.Movie
import com.example.core.utils.EspressoIdlingResource
import com.example.core.vo.ApiResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiClient: ApiClient) {

    fun getMovie(): Flowable<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val result = PublishSubject.create<ApiResponse<List<Movie>>>()
        val client = apiClient.getMovie()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val data = response.result
                result.onNext(
                    if (data.isNotEmpty()) {
                        ApiResponse.Success(data)
                    }
                    else
                        ApiResponse.Empty())
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))

            })
        EspressoIdlingResource.decrement()

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

}