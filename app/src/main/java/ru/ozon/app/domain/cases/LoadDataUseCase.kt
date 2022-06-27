package ru.ozon.app.domain.cases

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkManager
import com.srggrch.core.workers.LoadDetailsWorker
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val workManager: WorkManager
) {
    fun execute(): Operation {
        val loadPreviews = OneTimeWorkRequestBuilder<LoadDetailsWorker>().build()
        val loadDetails = OneTimeWorkRequestBuilder<LoadDetailsWorker>().build()

        return workManager
            .beginWith(loadPreviews)
            .then(loadDetails)
            .enqueue()
    }
}