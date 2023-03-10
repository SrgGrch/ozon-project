package tech.blur.core.domain

import java.util.*

interface FavoriteUseCase {
    suspend fun addToFavorite(uuid: UUID)
    suspend fun removeFromFavorite(uuid: UUID)
}