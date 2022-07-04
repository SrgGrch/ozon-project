package com.srggrch.core.domain

import com.srggrch.core.data.repos.ProductPreviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class UpdatePreviewListUseCaseImpl @Inject constructor(
    private val productPreviewRepository: ProductPreviewRepository
) : UpdatePreviewListUseCase {
    override suspend fun execute(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productPreviewRepository.update()
        }
    }
}