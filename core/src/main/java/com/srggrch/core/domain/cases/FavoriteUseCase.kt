package com.srggrch.core.domain.cases

import java.util.*

interface FavoriteUseCase {
    suspend fun addToFavorite(guid: UUID)
    suspend fun removeFromFavorite(guid: UUID)
}