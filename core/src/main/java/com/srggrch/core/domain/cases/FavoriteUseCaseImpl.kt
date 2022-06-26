package com.srggrch.core.domain.cases

import java.util.*
import javax.inject.Inject

internal class FavoriteUseCaseImpl @Inject constructor() : FavoriteUseCase {
    override suspend fun addToFavorite(guid: UUID) {
        //todo
    }

    override suspend fun removeFromFavorite(guid: UUID) {
        //todo
    }
}