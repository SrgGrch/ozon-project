package tech.blur.list.impl.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.blur.list.impl.ui.ListFragment

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesListFragment(): ListFragment
}