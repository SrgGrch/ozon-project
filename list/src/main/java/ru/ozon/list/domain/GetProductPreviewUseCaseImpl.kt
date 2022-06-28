package ru.ozon.list.domain

import com.srggrch.core.data.repos.ProductPreviewRepository
import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource
import javax.inject.Inject

internal class GetProductPreviewUseCaseImpl @Inject constructor(
    private val repository: ProductPreviewRepository
) : GetProductPreviewUseCase {
    override fun execute(): Flow<Resource<List<ProductPreview>>> {
        return repository.getProductsPreviews()
    }
}