package com.srggrch.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.srggrch.core.data.repos.ProductDetailsRepository
import com.srggrch.core.workers.factory.ChildWorkerFactory
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class LoadDetailsWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val productDetailsRepository: ProductDetailsRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return when (productDetailsRepository.update()) {
            is Resource.Success -> Result.success()
            else -> Result.failure()
        }
    }

    class Factory @Inject constructor(
        private val context: Context,
        private val productDetailsRepository: ProductDetailsRepository
    ) : ChildWorkerFactory {
        override fun create(params: WorkerParameters): CoroutineWorker {
            return LoadDetailsWorker(context, params, productDetailsRepository)
        }
    }
}