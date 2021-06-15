package com.example.chapstone

import android.app.Application
import com.example.chapstone.di.AppComponent
import com.example.chapstone.di.DaggerAppComponent
import com.example.core.data.di.CoreComponent
import com.example.core.data.di.DaggerCoreComponent


open class MyApplication : Application(){

    private val coreComponent : CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}