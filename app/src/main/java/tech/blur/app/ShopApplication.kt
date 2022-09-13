package tech.blur.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import tech.blur.app.di.ContextModule
import tech.blur.app.di.DaggerAppComponent
import tech.blur.core.workers.factory.MyWorkerFactory
import javax.inject.Inject


class ShopApplication : Application(), HasAndroidInjector {

    @Inject
    @JvmField
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    @Inject
    lateinit var workerFactory: MyWorkerFactory

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
            .inject(this)

        initWorkManager(workerFactory)
    }

    private fun initWorkManager(workerFactory: MyWorkerFactory) {
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
        )
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector!!
}