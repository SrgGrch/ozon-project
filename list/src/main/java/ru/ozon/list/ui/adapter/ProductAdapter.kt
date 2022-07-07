package ru.ozon.list.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ozon.list.ui.ProductPreviewItem

internal class ProductAdapter(
    vararg delegates: AdapterDelegate<List<ProductPreviewItem>>
) : AsyncListDifferDelegationAdapter<ProductPreviewItem>(DIFF_CALLBACK, *delegates) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductPreviewItem>() {
            override fun areItemsTheSame(
                oldItem: ProductPreviewItem,
                newItem: ProductPreviewItem
            ): Boolean = oldItem.uniqueTag == newItem.uniqueTag

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ProductPreviewItem,
                newItem: ProductPreviewItem
            ): Boolean = oldItem == newItem

            override fun getChangePayload(
                oldItem: ProductPreviewItem,
                newItem: ProductPreviewItem
            ): Any? {
                if (oldItem is ProductPreviewItem.ProductPreview && newItem is ProductPreviewItem.ProductPreview) {
                    if (
                        oldItem.isInCart != newItem.isInCart
                        || oldItem.isFavorite != newItem.isFavorite
                        || oldItem.isInProgress != newItem.isInProgress
                    ) {
                        return ProductsAdapterDelegate.Payload(
                            if (oldItem.isFavorite != newItem.isFavorite) newItem.isFavorite else null,
                            if (oldItem.isInCart != newItem.isInCart) newItem.isInCart else null,
                            if (oldItem.isInProgress != newItem.isInProgress) newItem.isInProgress else null
                        )
                    }
                }

                return null
            }
        }
    }

}