package tech.blur.addproduct.di

import dagger.Binds
import dagger.Module
import tech.blur.addproduct.domain.SaveProductUseCase
import tech.blur.addproduct.domain.SaveProductUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindSaveProductUseCase(impl: SaveProductUseCaseImpl): SaveProductUseCase
}