package ru.ozon.details.domain

import com.srggrch.core.data.models.Product
import com.srggrch.core.data.repos.ProductDetailsRepository
import ru.ozon.utils.data.Resource
import java.util.*
import javax.inject.Inject

internal class LoadDetailsUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
): LoadDetailsUseCase {
    override suspend fun execute(uuid: UUID): Resource<Product> {
        return productDetailsRepository.findProductDetails(uuid)
    }
}