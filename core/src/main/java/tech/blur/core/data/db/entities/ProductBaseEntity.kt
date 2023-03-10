package tech.blur.core.data.db.entities

import androidx.room.PrimaryKey
import java.util.*

internal data class ProductBaseEntity(
    @PrimaryKey val guid: UUID,
    val name: String,
    val price: String,
    val description: String?,
    val rating: Double,
    val images: List<String>,
    val weight: Double?,
    val count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>
)

internal fun ProductEntity.toBaseEntity() = ProductBaseEntity(
    guid,
    name,
    price,
    description,
    rating,
    images,
    weight,
    count,
    availableCount,
    additionalParams
)