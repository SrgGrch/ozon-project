package com.srggrch.core.data.storages

import com.srggrch.core.data.models.Product
import java.util.*

interface ProductDetailsStorage {
    suspend fun saveProducts(products: List<Product>): List<Product>
    suspend fun saveProduct(product: Product): Product
    suspend fun getAllProducts(): List<Product>
    suspend fun findProduct(uuid: UUID): Product?
    suspend fun setFavorite(uuid: UUID, isFavorite: Boolean)
}