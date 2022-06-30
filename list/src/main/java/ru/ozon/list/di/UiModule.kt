package ru.ozon.list.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ozon.list.ui.ListFragment

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesListFragment(): ListFragment
}