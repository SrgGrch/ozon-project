package ru.ozon.addproduct.di

import dagger.Binds
import dagger.Module
import ru.ozon.addproduct.domain.SaveProductUseCase
import ru.ozon.addproduct.domain.SaveProductUseCaseImpl

@Module
internal interface CaseModule {
    @Binds
    fun bindSaveProductUseCase(impl: SaveProductUseCaseImpl): SaveProductUseCase
}