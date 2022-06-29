package com.srggrch.core.workers.factory

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class MyWorkerFactory @Inject constructor(
    private val workers: Map<Class<out CoroutineWorker>, @JvmSuppressWildcards ChildWorkerFactory>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return workers.entries
            .find { it.key.name == workerClassName }
            ?.value
            ?.create(workerParameters)
            ?: throw NullPointerException("Can not find $workerClassName")
    }
}