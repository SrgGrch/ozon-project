package com.srggrch.core.data.repos

import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource
import java.util.*

interface ProductPreviewRepository {
    suspend fun update(): Resource<Unit>
    fun getProductsPreviews(): Flow<Resource<List<ProductPreview>>>
}