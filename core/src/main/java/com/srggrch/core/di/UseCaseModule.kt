package com.srggrch.core.di

import com.srggrch.core.domain.*
import dagger.Binds
import dagger.Module

@Module
internal interface UseCaseModule {
    @Binds
    fun bindFavoriteUseCase(impl: FavoriteUseCaseImpl): FavoriteUseCase

    @Binds
    fun bindUpdatePreviewListUseCase(impl: UpdatePreviewListUseCaseImpl): UpdatePreviewListUseCase

    @Binds
    fun bindUpdateDetailsUseCase(impl: UpdateDetailsUseCaseImpl): UpdateDetailsUseCase

    @Binds
    fun bindAddToCardUseCase(impl: AddToCardUseCaseImpl): AddToCardUseCase
}