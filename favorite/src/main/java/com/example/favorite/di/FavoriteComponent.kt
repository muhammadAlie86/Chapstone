package com.example.favorite.di

import com.example.chapstone.di.AppModule
import com.example.core.data.di.CoreComponent
import com.example.favorite.view.FavoriteFragment
import dagger.Component

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class,FavoriteViewModelModule::class]
)
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)
}