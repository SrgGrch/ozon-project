package com.srggrch.core.data.storages

import com.srggrch.core.data.models.ProductPreview

interface ProductPreviewStorage {
    suspend fun saveProducts(product: List<ProductPreview>): List<ProductPreview>
    suspend fun loadProducts(): List<ProductPreview>
}