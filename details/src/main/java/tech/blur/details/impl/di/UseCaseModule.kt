package tech.blur.details.impl.di

import dagger.Binds
import dagger.Module
import tech.blur.details.impl.domain.LoadDetailsUseCase
import tech.blur.details.impl.domain.LoadDetailsUseCaseImpl

@Module
internal interface UseCaseModule {
    @Binds
    fun bindLoadDetailsUseCase(impl: LoadDetailsUseCaseImpl): LoadDetailsUseCase
}