package tech.blur.addproduct.domain

import tech.blur.core.data.models.Product

interface SaveProductUseCase {
    suspend fun execute(product: Product)
}