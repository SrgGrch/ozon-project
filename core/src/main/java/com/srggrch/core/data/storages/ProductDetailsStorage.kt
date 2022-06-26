package com.srggrch.core.data.storages

import com.srggrch.core.data.models.Product

interface ProductDetailsStorage {
    suspend fun saveProducts(product: List<Product>): List<Product>
    suspend fun loadProducts(): List<Product>
}