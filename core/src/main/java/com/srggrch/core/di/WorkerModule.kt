package com.srggrch.core.di

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import com.srggrch.core.workers.LoadDetailsWorker
import com.srggrch.core.workers.LoadPreviewWorker
import com.srggrch.core.workers.factory.ChildWorkerFactory
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
    @WorkerKey(LoadDetailsWorker::class)
    internal abstract fun bindLoadDetailsWorker(worker: LoadDetailsWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(LoadPreviewWorker::class)
    internal abstract fun bindLoadPreviewWorker(worker: LoadPreviewWorker.Factory): ChildWorkerFactory

    companion object {
        @Provides
        internal fun provideWorkManager(context: Context) = WorkManager.getInstance(context)
    }
}