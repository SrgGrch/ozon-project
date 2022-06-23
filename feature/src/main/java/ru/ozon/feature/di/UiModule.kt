package ru.ozon.feature.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ozon.feature.ui.main.MainFragment

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesMainFragment(): MainFragment
}