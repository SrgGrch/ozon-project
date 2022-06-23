package ru.ozon.app.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.ozon.app.OzonApplication
import ru.ozon.feature.di.FeatureModule


@Component(modules = [
    AndroidInjectionModule::class,
    MainModule::class,
    MainActivityModule::class,
    FeatureModule::class
])
interface AppComponent {
    fun inject(application: OzonApplication)
}