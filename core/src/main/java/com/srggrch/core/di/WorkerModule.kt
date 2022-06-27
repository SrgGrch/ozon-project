package com.srggrch.core.di

import android.content.Context
import androidx.work.Configuration
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import com.srggrch.core.workers.LoadDetailsWorker
import com.srggrch.core.workers.LoadPreviewsWorker
import com.srggrch.core.workers.factory.ChildWorkerFactory
import com.srggrch.core.workers.factory.MyWorkerFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out CoroutineWorker>)

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(LoadPreviewsWorker::class)
    internal abstract fun bindLoadPreviewsWorker(worker: LoadPreviewsWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(LoadDetailsWorker::class)
    internal abstract fun bindLoadDetailsWorker(worker: LoadDetailsWorker.Factory): ChildWorkerFactory

    companion object {
        @Provides
        internal fun provideWorkManager(context: Context) = WorkManager.getInstance(context)
    }
}