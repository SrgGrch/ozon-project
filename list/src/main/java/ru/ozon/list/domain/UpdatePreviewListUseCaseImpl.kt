package ru.ozon.list.domain

import com.srggrch.core.data.repos.ProductPreviewRepository
import javax.inject.Inject

class UpdatePreviewListUseCaseImpl @Inject constructor(
    private val productPreviewRepository: ProductPreviewRepository
) : UpdatePreviewListUseCase {
    override suspend fun execute() {
        productPreviewRepository.update()
    }
}