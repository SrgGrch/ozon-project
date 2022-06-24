package com.srggrch.core.data.storages

import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview

interface ProductPreviewStorage {
    suspend fun saveProducts(product: List<ProductPreview>): List<ProductPreview>
    suspend fun loadProducts(): List<ProductPreview>
}