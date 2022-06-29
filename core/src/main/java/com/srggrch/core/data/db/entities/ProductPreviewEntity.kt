package com.srggrch.core.data.db.entities

import java.util.*


internal data class ProductPreviewEntity(
    val guid: UUID,
    val name: String,
    val images: List<String>,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)