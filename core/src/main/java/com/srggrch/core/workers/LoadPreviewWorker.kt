package com.srggrch.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.srggrch.core.domain.UpdatePreviewListUseCase
import com.srggrch.core.workers.factory.ChildWorkerFactory
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class LoadPreviewWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val updatePreviewListUseCase: UpdatePreviewListUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return when (updatePreviewListUseCase.execute()) {
            is Resource.Success -> Result.success()
            else -> Result.failure()
        }
    }

    class Factory @Inject constructor(
        private val context: Context,
        private val updatePreviewListUseCase: UpdatePreviewListUseCase
    ) : ChildWorkerFactory {
        override fun create(params: WorkerParameters): CoroutineWorker {
            return LoadPreviewWorker(context, params, updatePreviewListUseCase)
        }
    }
}