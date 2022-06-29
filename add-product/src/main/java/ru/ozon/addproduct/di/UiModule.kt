package ru.ozon.addproduct.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ozon.addproduct.ui.AddProductFragment
import ru.ozon.addproduct.ui.AddProductRouter
import ru.ozon.addproduct.ui.AddProductRouterImpl

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesDetailsFragment(): AddProductFragment

    @Binds
    fun bindDetailsRouter(impl: AddProductRouterImpl): AddProductRouter
}