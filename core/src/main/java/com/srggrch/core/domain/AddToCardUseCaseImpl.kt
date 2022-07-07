package com.srggrch.core.domain

import com.srggrch.core.data.repos.ProductDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

internal class AddToCardUseCaseImpl @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) : AddToCardUseCase {
    override suspend fun addToCart(uuid: UUID) {
        withContext(Dispatchers.Default) {
            delay(600) // todo remove (for testing purposes, because wiring in DB practically instant)
            productDetailsRepository.setAddToCart(uuid, true)
        }
    }

    override suspend fun removeFromCart(uuid: UUID) {
        withContext(Dispatchers.Default) {
            delay(600) // todo remove (for testing purposes, because wiring in DB practically instant)
            productDetailsRepository.setAddToCart(uuid, false)
        }
    }
}