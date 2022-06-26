package ru.ozon.list.di

import dagger.Binds
import dagger.Module
import ru.ozon.list.ui.domain.GetProductPreviewUseCase
import ru.ozon.list.ui.domain.GetProductPreviewUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindGetProductPreviewUseCase(impl: GetProductPreviewUseCaseImpl): GetProductPreviewUseCase
}