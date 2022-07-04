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

    override suspend fun saveProduct(product: Product) {
        productDetailsStorage.saveProduct(product)
    }

    override suspend fun findProductDetails(uuid: UUID): Resource<Product> {
        return productDetailsStorage.findProduct(uuid)?.let { Resource.newSuccess(it) }
            ?: Resource.newError()
    }


    override fun findProductDetailsFlow(uuid: UUID): Flow<Resource<Product>> {
        return flow {
            emit(Resource.newSuccess(productDetailsStorage.findProduct(uuid)!!))

            // todo load from server by id (currently now such api method)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun productViewed(uuid: UUID) {
        withContext(Dispatchers.IO) {
            productDetailsStorage.increaseViewCount(uuid)
        }
    }

    override suspend fun setFavorite(uuid: UUID, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            productDetailsStorage.setFavorite(uuid, isFavorite)
        }
    }
}