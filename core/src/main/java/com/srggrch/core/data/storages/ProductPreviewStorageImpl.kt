package com.srggrch.core.data.storages

import com.srggrch.core.data.db.dao.ProductPreviewDao
import com.srggrch.core.data.db.entities.ProductPreviewEntity
import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ProductPreviewStorageImpl @Inject constructor(
    private val productPreviewDao: ProductPreviewDao
) : ProductPreviewStorage {
    override suspend fun saveProductsPreviews(products: List<ProductPreview>): List<ProductPreview> {
        productPreviewDao.insertAll(products.map { it.toEntity() })
        return products
    }

    override suspend fun loadProductsPreviews(): List<ProductPreview> {
        return productPreviewDao.getAllProductPreviews()
            .map { it.toDomain() }
    }

    override fun loadProductsPreviewsFlow(): Flow<List<ProductPreview>> {
        return productPreviewDao.getAllProductPreviewsFlow()
            .map { it.map { p -> p.toDomain() } }
    }

    private fun ProductPreview.toEntity() = ProductPreviewEntity(
        guid, name, images, price, rating, isFavorite, isInCart
    )

    private fun ProductPreviewEntity.toDomain() = ProductPreview(
        guid, images, name, price, rating, isFavorite, isInCart
    )
}