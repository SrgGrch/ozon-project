package tech.blur.list.impl.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        UseCaseModule::class
    ]
)
interface ListModule