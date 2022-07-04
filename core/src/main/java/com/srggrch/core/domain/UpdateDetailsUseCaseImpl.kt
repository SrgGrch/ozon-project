package com.srggrch.core.domain

import com.srggrch.core.data.repos.ProductDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.utils.data.Resource

class UpdateDetailsUseCaseImpl(
    private val productDetailsRepository: ProductDetailsRepository
) : UpdateDetailsUseCase {
    override suspend fun execute(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productDetailsRepository.update()
        }
    }
}