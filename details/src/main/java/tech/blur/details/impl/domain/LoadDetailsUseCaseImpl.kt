package tech.blur.details.impl.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.blur.core.data.models.Product
import tech.blur.core.data.repos.ProductDetailsRepository
import tech.blur.utils.data.Resource
import java.util.*
import javax.inject.Inject

internal class LoadDetailsUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : LoadDetailsUseCase {
    override suspend fun execute(uuid: UUID): Resource<Product> {
        return withContext(Dispatchers.IO) {
            productDetailsRepository.findProductDetails(uuid)
        }
    }
}