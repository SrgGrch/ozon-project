package ru.ozon.app

//import ru.ozon.app.di.AppComponent
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.srggrch.core.workers.factory.MyWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.ozon.app.di.ContextModule
import ru.ozon.app.di.DaggerAppComponent
import javax.inject.Inject


class OzonApplication : Application(), HasAndroidInjector {

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