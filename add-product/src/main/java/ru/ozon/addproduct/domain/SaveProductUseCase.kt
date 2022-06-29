package ru.ozon.addproduct.domain

import com.srggrch.core.data.models.Product

interface SaveProductUseCase {
    suspend fun execute(product: Product)
}