package com.srggrch.core.data.repos

import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductDetailsStorage
import com.srggrch.core.data.storages.ProductPreviewStorage
import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.*
import java.util.*
import javax.inject.Inject

internal class ProductPreviewRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productPreviewStorage: ProductPreviewStorage
) : ProductPreviewRepository {
    override suspend fun update(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productService.getProducts()
                .doOnSuccess {
                    productPreviewStorage.saveProducts(it)
                }
                .mapDataToUnit()
        }
    }

    override fun getProductsPreviews(): Flow<Resource<List<ProductPreview>>> {
        return productPreviewStorage.loadProductsFlow()
            .map {
                Resource.newSuccess(it)
            }
            .onStart {
                update()
            }.flowOn(Dispatchers.IO)
    }
}