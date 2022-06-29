package ru.ozon.addproduct.domain

import com.srggrch.core.data.models.Product
import com.srggrch.core.data.repos.ProductDetailsRepository
import javax.inject.Inject

class SaveProductUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
): SaveProductUseCase {
    override suspend fun execute(product: Product) {
        productDetailsRepository.saveProduct(product)
    }
}