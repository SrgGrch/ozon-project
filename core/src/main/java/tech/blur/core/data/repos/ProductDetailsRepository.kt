package tech.blur.core.data.repos

import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.models.Product
import tech.blur.utils.data.Resource
import java.util.*

interface ProductDetailsRepository {
    suspend fun update(): Resource<Unit>
    suspend fun saveProduct(product: Product)
    suspend fun findProductDetails(uuid: UUID): Resource<Product>
    fun findProductDetailsFlow(uuid: UUID): Flow<Resource<Product>>
    suspend fun setFavorite(uuid: UUID, isFavorite: Boolean)
    suspend fun productViewed(uuid: UUID)
    suspend fun setAddToCart(uuid: UUID, isInCart: Boolean)
}