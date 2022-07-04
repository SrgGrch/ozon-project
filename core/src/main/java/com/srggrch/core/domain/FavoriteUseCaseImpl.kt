package com.srggrch.core.domain

import com.srggrch.core.data.repos.ProductDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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