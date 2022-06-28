package com.srggrch.core.data.storages

import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow

interface ProductPreviewStorage {
    suspend fun saveProducts(products: List<ProductPreview>): List<ProductPreview>
    suspend fun loadProducts(): List<ProductPreview>
    fun loadProductsFlow(): Flow<List<ProductPreview>>
}