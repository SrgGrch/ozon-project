package com.srggrch.core.workers.factory

import android.content.Context
import androidx.work.*
import javax.inject.Inject
import javax.inject.Provider

class MyWorkerFactory @Inject constructor(
    private val workers: Map<Class<out CoroutineWorker>, @JvmSuppressWildcards Provider<Worker>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return workers.entries.find { it.key.simpleName == workerClassName }?.value?.get()
            ?: throw NullPointerException("Can not find $workerClassName")
    }
}