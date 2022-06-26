package com.srggrch.core.data.services

import com.srggrch.core.data.api.Api
import com.srggrch.core.data.api.runRequest
import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import ru.ozon.utils.data.Resource
import javax.inject.Inject

internal class ProductServiceImpl @Inject constructor(private val api: Api) : ProductService {
    override suspend fun getProducts(): Resource<List<ProductPreview>> {
        return api.runRequest {
            getProducts()
        }
    }

    override suspend fun getProductDetails(): Resource<List<Product>> {
        return api.runRequest {
            getProductDetails()
        }
    }
}