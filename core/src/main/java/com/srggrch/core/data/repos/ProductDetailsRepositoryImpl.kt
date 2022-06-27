package com.srggrch.core.data.repos

import com.srggrch.core.data.models.Product
import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductDetailsStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.Resource
import ru.ozon.utils.data.doOnSuccess
import ru.ozon.utils.data.mapDataToUnit
import ru.ozon.utils.data.mapError
import java.util.*
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDetailsStorage: ProductDetailsStorage
) : ProductDetailsRepository {
    override suspend fun update(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productService.getProductDetails()
                .doOnSuccess {
                    productDetailsStorage.saveProducts(it)
                }
                .mapDataToUnit()
        }
    }

    override fun getProductDetails(): Flow<Resource<List<Product>>> {
        return flow {
            val localData = productDetailsStorage.getAllProducts().takeIf { it.isNotEmpty() }
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
        return withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
    }

    override suspend fun productViewed(uuid: UUID) {
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
    }
}