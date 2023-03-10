package tech.blur.core.domain

import java.util.*

interface AddToCardUseCase {
    suspend fun addToCart(uuid: UUID)
    suspend fun removeFromCart(uuid: UUID)
}