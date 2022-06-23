package ru.ozon.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ozon.app.MainActivity

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeYourAndroidInjector(): MainActivity
}