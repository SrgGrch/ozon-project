package tech.blur.addproduct.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.blur.core.data.models.Product
import tech.blur.core.data.repos.ProductDetailsRepository
import javax.inject.Inject

class SaveProductUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : SaveProductUseCase {
    override suspend fun execute(product: Product) {
        withContext(Dispatchers.IO) {
            productDetailsRepository.saveProduct(product)
        }
    }
}