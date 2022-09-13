package tech.blur.details.impl.domain

import tech.blur.core.data.models.Product
import tech.blur.utils.data.Resource
import java.util.*

interface LoadDetailsUseCase {
    suspend fun execute(uuid: UUID): Resource<Product>
}