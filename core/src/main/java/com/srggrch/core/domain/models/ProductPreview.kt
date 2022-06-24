package com.srggrch.core.domain.models

import java.util.*

data class ProductPreview(
    val guid: UUID,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)