package com.srggrch.core.di

import com.srggrch.core.domain.FavoriteUseCase
import com.srggrch.core.domain.FavoriteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UseCaseModule {
    @Binds
    fun bindFavoriteUseCase(impl: FavoriteUseCaseImpl): FavoriteUseCase
}