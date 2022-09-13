package tech.blur.core.data.services

import tech.blur.core.data.models.Product
import tech.blur.core.data.models.ProductPreview
import tech.blur.utils.data.Resource

interface ProductService {
    suspend fun getProducts(): Resource<List<ProductPreview>>
    suspend fun getProductDetails(): Resource<List<Product>>
}