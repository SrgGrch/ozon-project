package tech.blur.core.data.models

import java.util.*

data class Product(
    val guid: UUID,
    val name: String,
    val price: String,
    val description: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val weight: Double?,
    val count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>,
    val viewsCount: Int = 0
)