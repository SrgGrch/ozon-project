package tech.blur.list.impl.domain

import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.models.ProductPreview
import tech.blur.utils.data.Resource

interface GetProductPreviewUseCase {
    fun execute(): Flow<Resource<List<ProductPreview>>>
}