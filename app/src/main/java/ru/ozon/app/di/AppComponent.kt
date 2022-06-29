package ru.ozon.app.di

import com.srggrch.core.di.CoreModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.ozon.app.OzonApplication
import ru.ozon.details.di.DetailsModule
import ru.ozon.list.di.ListModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        ListModule::class,
        DetailsModule::class,
        ContextModule::class,
        CoreModule::class
    ]
)
interface AppComponent {
    fun inject(application: OzonApplication)
}