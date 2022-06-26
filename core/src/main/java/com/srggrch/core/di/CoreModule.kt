package com.srggrch.core.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        WorkerModule::class,
        StorageModule::class,
        RepoModule::class,
        ServiceModule::class,
        UseCaseModule::class
    ]
)
class CoreModule