package ru.ozon.list.ui

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.coreui.recycler.AdapterItem
import ru.ozon.list.databinding.ItemProductPreviewBinding
import com.srggrch.coreui.R as CoreR

internal object ProductsAdapterDelegate {
    operator fun invoke(
        onAddToCardClicked: (item: ProductPreviewItem) -> Unit,
        onFavoriteClicked: (item: ProductPreviewItem) -> Unit,
        onItemClicked: (item: ProductPreviewItem) -> Unit
    ): AdapterDelegate<List<AdapterItem>> = adapterDelegateViewBinding<ProductPreviewItem, _, _>(
        viewBinding = { inflater, parent ->
            ItemProductPreviewBinding.inflate(inflater, parent, false)
        }
    ) {
        with(binding) {
            val glide = Glide.with(itemView)

            addToCart.setOnClickListener {
                onAddToCardClicked(item)
            }

            root.setOnClickListener {
                onItemClicked(item)
            }

            fav.setOnClickListener {
                onFavoriteClicked(item)
            }

            bind {
                price.text = item.price
                name.text = item.name

                fav.setImageResource(
                    if (item.isFavorite) {
                        CoreR.drawable.ic_favorite_20
                    } else {
                        CoreR.drawable.ic_favorite_border_20
                    }
                )

                rating.rating = item.rating.toFloat()

                glide
                    .load(item.image)
                    .into(image)
            }
        }
    }
}