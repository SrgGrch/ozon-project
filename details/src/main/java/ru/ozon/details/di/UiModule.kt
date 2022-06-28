package ru.ozon.details.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ozon.details.ui.DetailsRouter
import ru.ozon.details.ui.DetailsRouterImpl
import ru.ozon.details.ui.ProductDetailsFragment

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesDetailsFragment(): ProductDetailsFragment

    @Binds
    fun bindDetailsRouter(impl: DetailsRouterImpl): DetailsRouter
}