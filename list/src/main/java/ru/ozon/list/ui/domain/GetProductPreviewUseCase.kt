package ru.ozon.list.ui.domain

import com.srggrch.core.data.models.ProductPreview
import kotlinx.coroutines.flow.Flow
import ru.ozon.utils.data.Resource

interface GetProductPreviewUseCase {
    fun execute(): Flow<Resource<List<ProductPreview>>>
}