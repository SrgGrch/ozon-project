package com.srggrch.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.srggrch.core.data.repos.ProductPreviewRepository
import com.srggrch.core.workers.factory.ChildWorkerFactory
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class LoadPreviewWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val productPreviewRepository: ProductPreviewRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return when (productPreviewRepository.update()) {
            is Resource.Success -> Result.success()
            else -> Result.failure()
        }
    }

    class Factory @Inject constructor(
        private val context: Context,
        private val productPreviewRepository: ProductPreviewRepository
    ) : ChildWorkerFactory {
        override fun create(params: WorkerParameters): CoroutineWorker {
            return LoadPreviewWorker(context, params, productPreviewRepository)
        }
    }
}