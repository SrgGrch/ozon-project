package com.srggrch.core.workers.factory

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(params: WorkerParameters): CoroutineWorker
}