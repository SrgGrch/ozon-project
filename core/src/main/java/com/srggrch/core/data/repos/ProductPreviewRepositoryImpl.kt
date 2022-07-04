package com.srggrch.core.data.repos

import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductPreviewStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.Resource
import ru.ozon.utils.data.doOnSuccess
import ru.ozon.utils.data.mapDataToUnit
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