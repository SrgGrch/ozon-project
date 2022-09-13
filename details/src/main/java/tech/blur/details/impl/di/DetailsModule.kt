package tech.blur.details.impl.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        UseCaseModule::class
    ]
)
interface DetailsModule