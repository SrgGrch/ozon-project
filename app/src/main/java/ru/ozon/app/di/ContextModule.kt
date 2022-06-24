package ru.ozon.app.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class ContextModule(private val context: Context) {
    @Provides
    fun provideContext() = context
}