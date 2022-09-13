package tech.blur.details.impl.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.blur.details.impl.ui.DetailsRouter
import tech.blur.details.impl.ui.DetailsRouterImpl
import tech.blur.details.impl.ui.ProductDetailsFragment

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesDetailsFragment(): ProductDetailsFragment

    @Binds
    fun bindDetailsRouter(impl: DetailsRouterImpl): DetailsRouter
}