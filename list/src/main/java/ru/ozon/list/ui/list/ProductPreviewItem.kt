package ru.ozon.list.ui.list

import ru.ozon.utils.ui.recycler.AdapterItem
import java.util.*

internal data class ProductPreviewItem(
    val guid: UUID,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
) : AdapterItem {
    override val uniqueTag: String
        get() = guid.toString()
}
