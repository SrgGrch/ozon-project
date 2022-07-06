package com.srggrch.core.data.storages

import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow

interface ProductPreviewStorage {
    suspend fun saveProductsPreviews(products: List<ProductPreview>): List<ProductPreview>
    suspend fun loadProductsPreviews(): List<ProductPreview>
    fun loadProductsPreviewsFlow(): Flow<List<ProductPreview>>
}