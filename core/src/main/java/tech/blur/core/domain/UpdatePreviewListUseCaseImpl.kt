package tech.blur.core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.blur.core.data.repos.ProductPreviewRepository
import tech.blur.utils.data.Resource
import javax.inject.Inject

internal class UpdatePreviewListUseCaseImpl @Inject constructor(
    private val productPreviewRepository: ProductPreviewRepository
) : UpdatePreviewListUseCase {
    override suspend fun execute(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productPreviewRepository.update()
        }
    }
}