package ru.ozon.details.di

import dagger.Binds
import dagger.Module
import ru.ozon.details.domain.LoadDetailsUseCase
import ru.ozon.details.domain.LoadDetailsUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindLoadDetailsUseCase(impl: LoadDetailsUseCaseImpl): LoadDetailsUseCase
}