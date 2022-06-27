package com.srggrch.core.data.repos

import com.srggrch.core.data.models.Product
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource
import java.util.*

interface ProductDetailsRepository {
    suspend fun update(): Resource<Unit>
    fun getProductDetails(): Flow<Resource<List<Product>>>
    suspend fun findProductDetails(uuid: UUID): Flow<Resource<Product>>
    suspend fun productViewed(uuid: UUID)
}