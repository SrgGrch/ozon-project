package tech.blur.list.impl.di

import dagger.Binds
import dagger.Module
import tech.blur.list.impl.domain.GetProductPreviewUseCase
import tech.blur.list.impl.domain.GetProductPreviewUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindGetProductPreviewUseCase(impl: GetProductPreviewUseCaseImpl): GetProductPreviewUseCase
}