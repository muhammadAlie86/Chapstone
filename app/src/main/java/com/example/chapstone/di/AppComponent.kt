package com.example.chapstone.di

import com.example.chapstone.view.fragment.DetailFragment
import com.example.chapstone.view.fragment.MovieFragment
import com.example.core.data.di.CoreComponent
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class,ViewModelModule::class]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: MovieFragment)
    fun inject(fragment: DetailFragment)
}
