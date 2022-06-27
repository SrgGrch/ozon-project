package com.srggrch.core.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
internal data class ProductEntity(
    @PrimaryKey val guid: UUID,
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
    val additionalParams: Map<String, String>
)