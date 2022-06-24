package com.srggrch.core.data.storages

import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview

interface ProductDetailsStorage {
    suspend fun saveProducts(product: List<Product>): List<Product>
    suspend fun loadProducts(): List<Product>
}