package com.srggrch.core.domain.cases

import com.srggrch.core.data.repos.ProductDetailsRepository
import java.util.*
import javax.inject.Inject

internal class FavoriteUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : FavoriteUseCase {
    override suspend fun addToFavorite(uuid: UUID) {
        productDetailsRepository.setFavorite(uuid, true)
    }

    override suspend fun removeFromFavorite(uuid: UUID) {
        productDetailsRepository.setFavorite(uuid, false)
    }
}