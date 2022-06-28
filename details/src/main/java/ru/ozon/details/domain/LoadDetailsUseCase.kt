package ru.ozon.details.domain

import com.srggrch.core.data.models.Product
import ru.ozon.utils.data.Resource
import java.util.*

interface LoadDetailsUseCase {
    suspend fun execute(uuid: UUID): Resource<Product>
}