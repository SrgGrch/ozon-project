package com.srggrch.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.srggrch.core.data.api.Api
import com.srggrch.core.data.services.ProductService
import com.srggrch.core.workers.factory.ChildWorkerFactory
import javax.inject.Inject

class LoadListWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val productService: ProductService
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        val RESULT = "${LoadListWorker::class.simpleName}.result"
    }

    override suspend fun doWork(): Result {
        productService.getProducts()
        return Result.success()
    }

    class Factory @Inject constructor(
        private val context: Context,
        private val productService: ProductService
    ) : ChildWorkerFactory {
        override fun create(params: WorkerParameters): CoroutineWorker {
            return LoadListWorker(context, params, productService)
        }
    }
}