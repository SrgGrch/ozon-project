package tech.blur.core.di

import dagger.Binds
import dagger.Module
import tech.blur.core.domain.*

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