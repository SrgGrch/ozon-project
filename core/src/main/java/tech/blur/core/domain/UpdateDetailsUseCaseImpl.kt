package tech.blur.core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.blur.core.data.repos.ProductDetailsRepository
import tech.blur.utils.data.Resource
import javax.inject.Inject

internal class UpdateDetailsUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : UpdateDetailsUseCase {
    override suspend fun execute(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            productDetailsRepository.update()
        }
    }
}