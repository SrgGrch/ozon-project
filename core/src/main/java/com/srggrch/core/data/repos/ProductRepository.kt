package com.srggrch.core.data.repos

import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource
import java.util.*

interface ProductRepository {
    suspend fun update(): Resource<Unit>
    fun getProducts(): Flow<Resource<List<ProductPreview>>>
    fun getProductDetails(): Flow<Resource<List<Product>>>
    suspend fun findProductDetails(uuid: UUID): Flow<Resource<Product>>
    suspend fun productViewed(uuid: UUID)
}