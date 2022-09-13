package tech.blur.app.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import tech.blur.addproduct.di.AddModule
import tech.blur.app.ShopApplication
import tech.blur.core.di.CoreModule
import tech.blur.details.impl.di.DetailsModule
import tech.blur.list.impl.di.ListModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        ListModule::class,
        DetailsModule::class,
        ContextModule::class,
        AddModule::class,
        CoreModule::class
    ]
)
interface AppComponent {
    fun inject(application: ShopApplication)
}