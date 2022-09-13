package tech.blur.list.impl.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import tech.blur.core.data.models.ProductPreview
import tech.blur.core.data.repos.ProductPreviewRepository
import tech.blur.utils.data.Resource
import javax.inject.Inject

internal class GetProductPreviewUseCaseImpl @Inject constructor(
    private val repository: ProductPreviewRepository
) : GetProductPreviewUseCase {
    override fun execute(): Flow<Resource<List<ProductPreview>>> {
        return repository.getProductsPreviews().flowOn(Dispatchers.IO)
    }
}