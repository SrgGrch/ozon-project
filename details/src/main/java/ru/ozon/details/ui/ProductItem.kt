package ru.ozon.details.ui

import ru.ozon.coreui.PrintableText
import java.util.*

data class ProductItem(
    val guid: UUID,
    val name: PrintableText,
    val price: PrintableText,
    val description: PrintableText,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val count: PrintableText?,
    val availableCount: PrintableText?,
    val additionalParams: List<AdditionalParam>
) {
    data class AdditionalParam(val title: PrintableText, val value: PrintableText)
}
