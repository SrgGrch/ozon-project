package ru.ozon.details.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        CaseModule::class
    ]
)
interface DetailsModule