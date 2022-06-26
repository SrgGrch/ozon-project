package com.srggrch.core.di

import com.srggrch.core.domain.cases.FavoriteUseCase
import com.srggrch.core.domain.cases.FavoriteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UseCaseModule {
    @Binds
    fun bindFavoriteUseCase(impl: FavoriteUseCaseImpl): FavoriteUseCase
}