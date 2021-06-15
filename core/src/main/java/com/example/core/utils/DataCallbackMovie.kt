package com.example.core.utils

import com.example.core.domain.model.MovieEntityModel

interface DataCallbackMovie {
    fun setDataMovie(data: MovieEntityModel)
}