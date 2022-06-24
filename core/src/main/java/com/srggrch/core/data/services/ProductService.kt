package com.srggrch.core.data.services

import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview
import ru.ozon.utils.data.Resource

interface ProductService {
    suspend fun getProducts(): Resource<List<ProductPreview>>
    suspend fun getProductDetails(): Resource<List<Product>>
}