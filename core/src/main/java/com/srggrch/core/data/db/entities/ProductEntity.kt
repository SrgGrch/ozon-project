package com.srggrch.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
internal data class ProductEntity(
    @PrimaryKey
    val guid: UUID,
    val name: String,
    val price: String,
    val description: String? = null,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val weight: Double? = null,
    val count: Int? = null,
    val availableCount: Int? = null,
    @ColumnInfo(defaultValue = "{}")
    val additionalParams: Map<String, String> = emptyMap()
)