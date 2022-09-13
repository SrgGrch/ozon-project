package tech.blur.list.impl.ui

import tech.blur.coreui.PrintableText
import tech.blur.coreui.recycler.AdapterItem
import java.util.*

internal sealed interface ProductPreviewItem : AdapterItem {
    data class Header(val title: PrintableText) : ProductPreviewItem {
        override val uniqueTag: String
            get() = title.toString()
    }

    data class ProductPreview(
        val guid: UUID,
        val images: List<String>,
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
