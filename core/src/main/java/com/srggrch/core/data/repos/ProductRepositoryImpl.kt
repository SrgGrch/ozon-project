package com.srggrch.core.data.repos

import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductDetailsStorage
import com.srggrch.core.data.storages.ProductPreviewStorage
import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.*
import java.util.*
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productPreviewStorage: ProductPreviewStorage,
    private val productDetailsStorage: ProductDetailsStorage
) : ProductRepository {
    override suspend fun update(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productService.getProducts()
                .doOnSuccess {
                    productPreviewStorage.saveProducts(it)
                }
                .mapDataToUnit()
        }
    }

    override fun getProducts(): Flow<Resource<List<ProductPreview>>> {
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

    override fun getProductDetails(): Flow<Resource<List<Product>>> {
        return flow {
            val localData = productDetailsStorage.loadProducts().takeIf { it.isNotEmpty() }
            emit(
                Resource.newLoading(
                    localData
                )
            )

            emit(
                productService.getProductDetails()
                    .doOnSuccess {
                        productDetailsStorage.saveProducts(it)
                    }
                    .mapError {
                        it.copy(data = localData)
                    }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun findProductDetails(uuid: UUID): Flow<Resource<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun productViewed(uuid: UUID) {
        TODO("Not yet implemented")
    }
}