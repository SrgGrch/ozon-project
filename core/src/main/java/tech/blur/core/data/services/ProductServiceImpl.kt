package tech.blur.core.data.services

import tech.blur.core.data.api.Api
import tech.blur.core.data.api.runRequest
import tech.blur.core.data.models.Product
import tech.blur.core.data.models.ProductPreview
import tech.blur.utils.data.Resource
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