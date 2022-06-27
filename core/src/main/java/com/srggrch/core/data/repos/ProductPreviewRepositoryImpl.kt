package com.srggrch.core.data.repos

import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductDetailsStorage
import com.srggrch.core.data.storages.ProductPreviewStorage
import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        return flow {
            val localData = productPreviewStorage.loadProducts().takeIf { it.isNotEmpty() }

            emit(
                Resource.newLoading(
                    localData
                )
            )

            emit(
                productService.getProducts()
                    .doOnSuccess {
                        productPreviewStorage.saveProducts(it)
                    }
                    .mapError {
                        it.copy(data = localData)
                    }
            )
        }.flowOn(Dispatchers.IO)
    }
}