package tech.blur.core.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductPreview(
    val guid: UUID,
    @SerializedName("image")
    val images: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)