package ru.ozon.list.domain

import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.data.repos.ProductPreviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.ozon.utils.data.Resource
import javax.inject.Inject

internal class GetProductPreviewUseCaseImpl @Inject constructor(
    private val repository: ProductPreviewRepository
) : GetProductPreviewUseCase {
    override fun execute(): Flow<Resource<List<ProductPreview>>> {
        return repository.getProductsPreviews().flowOn(Dispatchers.IO)
    }
}