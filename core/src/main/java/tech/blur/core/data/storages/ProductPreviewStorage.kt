package tech.blur.core.data.storages

import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.models.ProductPreview

interface ProductPreviewStorage {
    suspend fun saveProductsPreviews(products: List<ProductPreview>): List<ProductPreview>
    suspend fun loadProductsPreviews(): List<ProductPreview>
    fun loadProductsPreviewsFlow(): Flow<List<ProductPreview>>
}