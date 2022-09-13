package tech.blur.app.domain.cases

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkManager
import tech.blur.core.workers.LoadDetailsWorker
import tech.blur.core.workers.LoadPreviewWorker
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val workManager: WorkManager
) {
    fun execute(): Operation {
        val loadPreviews = OneTimeWorkRequestBuilder<LoadPreviewWorker>().build()
        val loadDetails = OneTimeWorkRequestBuilder<LoadDetailsWorker>().build()

        return workManager
            .beginWith(loadPreviews)
            .then(loadDetails)
            .enqueue()
    }
}