package ru.ozon.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ContextModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context
}