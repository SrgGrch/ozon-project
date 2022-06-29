package ru.ozon.addproduct.di

import dagger.Module

@Module(
    includes = [
        UiModule::class,
        CaseModule::class
    ]
)
interface AddModule