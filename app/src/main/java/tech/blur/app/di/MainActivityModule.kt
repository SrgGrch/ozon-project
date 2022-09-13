package tech.blur.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.blur.app.MainActivity

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeYourAndroidInjector(): MainActivity
}