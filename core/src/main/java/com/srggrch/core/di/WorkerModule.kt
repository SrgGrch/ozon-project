package com.srggrch.core.di

import androidx.work.CoroutineWorker
import androidx.work.Worker
import com.srggrch.core.workers.LoadListWorker
import com.srggrch.core.workers.factory.ChildWorkerFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out CoroutineWorker>)

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(LoadListWorker::class)
    internal abstract fun bindMyWorkerFactory(worker: LoadListWorker.Factory): ChildWorkerFactory
}