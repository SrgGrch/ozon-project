package tech.blur.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import tech.blur.core.domain.UpdateDetailsUseCase
import tech.blur.core.workers.factory.ChildWorkerFactory
import tech.blur.utils.data.Resource
import javax.inject.Inject

class LoadDetailsWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val updateDetailsUseCase: UpdateDetailsUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return when (updateDetailsUseCase.execute()) {
            is Resource.Success -> Result.success()
            else -> Result.failure()
        }
    }

    class Factory @Inject constructor(
        private val context: Context,
        private val updateDetailsUseCase: UpdateDetailsUseCase
    ) : ChildWorkerFactory {
        override fun create(params: WorkerParameters): CoroutineWorker {
            return LoadDetailsWorker(context, params, updateDetailsUseCase)
        }
    }
}