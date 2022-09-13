package tech.blur.core.di

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import tech.blur.core.workers.LoadDetailsWorker
import tech.blur.core.workers.LoadPreviewWorker
import tech.blur.core.workers.factory.ChildWorkerFactory
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out CoroutineWorker>)

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(LoadDetailsWorker::class)
    internal abstract fun bindLoadDetailsWorker(worker: LoadDetailsWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(LoadPreviewWorker::class)
    internal abstract fun bindLoadPreviewWorker(worker: LoadPreviewWorker.Factory): ChildWorkerFactory

    companion object {
        @Provides
        internal fun provideWorkManager(context: Context) = WorkManager.getInstance(context)
    }
}