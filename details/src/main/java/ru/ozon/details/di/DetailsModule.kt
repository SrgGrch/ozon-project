package ru.ozon.details.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        UseCaseModule::class
    ]
)
interface DetailsModule