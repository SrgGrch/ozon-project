package tech.blur.addproduct.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        UseCaseModule::class
    ]
)
interface AddModule