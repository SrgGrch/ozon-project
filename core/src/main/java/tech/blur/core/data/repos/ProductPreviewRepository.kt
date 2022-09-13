package tech.blur.core.data.repos

import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.models.ProductPreview
import tech.blur.utils.data.Resource

interface ProductPreviewRepository {
    suspend fun update(): Resource<Unit>
    fun getProductsPreviews(): Flow<Resource<List<ProductPreview>>>
}