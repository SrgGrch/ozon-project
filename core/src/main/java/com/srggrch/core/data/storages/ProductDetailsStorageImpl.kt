package com.srggrch.core.data.storages

import com.srggrch.core.data.models.Product
import javax.inject.Inject

internal class ProductDetailsStorageImpl @Inject constructor() : ProductDetailsStorage {
    override suspend fun saveProducts(product: List<Product>): List<Product> {
        // todo
        return product
    }

    override suspend fun loadProducts(): List<Product> {
        return listOf()
    }
}