package com.srggrch.core.domain.cases

import java.util.*

interface FavoriteUseCase {
    suspend fun addToFavorite(uuid: UUID)
    suspend fun removeFromFavorite(uuid: UUID)
}