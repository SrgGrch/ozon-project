package ru.ozon.list.di

import dagger.Binds
import dagger.Module
import ru.ozon.list.domain.GetProductPreviewUseCase
import ru.ozon.list.domain.GetProductPreviewUseCaseImpl
import ru.ozon.list.domain.UpdatePreviewListUseCase
import ru.ozon.list.domain.UpdatePreviewListUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindGetProductPreviewUseCase(impl: GetProductPreviewUseCaseImpl): GetProductPreviewUseCase

    @Binds
    fun bindUpdatePreviewListUseCase(impl: UpdatePreviewListUseCaseImpl): UpdatePreviewListUseCase
}