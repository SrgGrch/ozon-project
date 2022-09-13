package tech.blur.addproduct.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.blur.addproduct.ui.AddProductFragment
import tech.blur.addproduct.ui.AddProductRouter
import tech.blur.addproduct.ui.AddProductRouterImpl

@Module
internal interface UiModule {
    @ContributesAndroidInjector
    fun contributesDetailsFragment(): AddProductFragment

    @Binds
    fun bindDetailsRouter(impl: AddProductRouterImpl): AddProductRouter
}