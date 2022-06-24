package com.srggrch.core.data.storages

import com.srggrch.core.domain.models.Product
import javax.inject.Inject

class ProductDetailsStorageImpl @Inject constructor() : ProductDetailsStorage {
    override suspend fun saveProducts(product: List<Product>): List<Product> {
        // todo
        return product
    }

    override suspend fun loadProducts(): List<Product> {
        return listOf()
    }
}