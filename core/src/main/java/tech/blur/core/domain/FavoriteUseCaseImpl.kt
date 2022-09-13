package tech.blur.core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.blur.core.data.repos.ProductDetailsRepository
import java.util.*
import javax.inject.Inject

internal class FavoriteUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : FavoriteUseCase {
    override suspend fun addToFavorite(uuid: UUID) {
        withContext(Dispatchers.IO) {
            productDetailsRepository.setFavorite(uuid, true)
        }
    }

    override suspend fun removeFromFavorite(uuid: UUID) {
        withContext(Dispatchers.IO) {
            productDetailsRepository.setFavorite(uuid, false)
        }
    }
}