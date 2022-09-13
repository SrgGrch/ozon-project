package tech.blur.core.data.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.blur.core.data.models.Product
import tech.blur.core.data.services.ProductService
import tech.blur.core.data.storages.ProductDetailsStorage
import tech.blur.utils.data.Resource
import tech.blur.utils.data.doOnSuccess
import tech.blur.utils.data.mapDataToUnit
import java.util.*
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDetailsStorage: ProductDetailsStorage
) : ProductDetailsRepository {
    override suspend fun update(): Resource<Unit> {
        return productService.getProductDetails()
            .doOnSuccess {
                productDetailsStorage.saveProducts(it)
            }
            .mapDataToUnit()

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
        }
    }

    override suspend fun productViewed(uuid: UUID) {
        productDetailsStorage.increaseViewCount(uuid)
    }

    override suspend fun setAddToCart(uuid: UUID, isInCart: Boolean) {
        productDetailsStorage.setAddToCart(uuid, isInCart)
    }

    override suspend fun setFavorite(uuid: UUID, isFavorite: Boolean) {
        productDetailsStorage.setFavorite(uuid, isFavorite)
    }
}