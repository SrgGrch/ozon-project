package com.srggrch.core.data.storages

import com.srggrch.core.domain.models.ProductPreview
import javax.inject.Inject

internal class ProductPreviewStorageImpl @Inject constructor() : ProductPreviewStorage {
    override suspend fun saveProducts(product: List<ProductPreview>): List<ProductPreview> {
        // todo
        return product
    }

    override suspend fun loadProducts(): List<ProductPreview> {
        return listOf()
    }
}