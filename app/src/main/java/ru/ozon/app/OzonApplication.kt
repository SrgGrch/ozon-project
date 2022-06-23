package ru.ozon.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.ozon.app.di.DaggerAppComponent
//import ru.ozon.app.di.AppComponent
import javax.inject.Inject


class OzonApplication : Application(), HasAndroidInjector {

    @Inject
    @JvmField
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector!!
}