package ru.ozon.list.ui

import ru.ozon.coreui.PrintableText
import ru.ozon.coreui.recycler.AdapterItem
import java.util.*

internal sealed interface ProductPreviewItem : AdapterItem {
    data class Header(val title: PrintableText) : ProductPreviewItem {
        override val uniqueTag: String
            get() = title.toString()
    }

    data class ProductPreview(
        val guid: UUID,
        val image: String,
        val name: PrintableText,
        val price: PrintableText,
        val rating: Double,
        val isFavorite: Boolean,
        val isInCart: Boolean,
        val isInProgress: Boolean = false
    ) : ProductPreviewItem {
        override val uniqueTag: String
            get() = guid.toString()
    }
}
