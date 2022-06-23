package ru.ozon.app.di

import android.content.Context
import androidx.activity.ComponentActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.ozon.app.MainActivity
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

}
