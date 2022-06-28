package com.srggrch.core.data.repos

import com.srggrch.core.data.models.Product
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource
import java.util.*

interface ProductDetailsRepository {
    suspend fun update(): Resource<Unit>
    suspend fun findProductDetails(uuid: UUID): Resource<Product>
    fun findProductDetailsFlow(uuid: UUID): Flow<Resource<Product>>
    suspend fun setFavorite(uuid: UUID, isFavorite: Boolean)
    suspend fun productViewed(uuid: UUID)
}