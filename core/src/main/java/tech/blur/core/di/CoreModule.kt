package tech.blur.core.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        WorkerModule::class,
        StorageModule::class,
        RepoModule::class,
        ServiceModule::class,
        DbModule::class,
        ConnectivityModule::class,
        UseCaseModule::class
    ]
)
class CoreModule