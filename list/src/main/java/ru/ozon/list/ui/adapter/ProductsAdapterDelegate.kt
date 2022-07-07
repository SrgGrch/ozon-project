package ru.ozon.list.ui.adapter

import android.widget.ImageButton
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.coreui.setPrintableText
import ru.ozon.coreui.views.ProgressButton
import ru.ozon.list.databinding.ItemHeaderBinding
import ru.ozon.list.databinding.ItemProductPreviewBinding
import ru.ozon.list.ui.ProductPreviewItem

internal object ProductsAdapterDelegate {
    operator fun invoke(
        onAddToCardClicked: (item: ProductPreviewItem.ProductPreview) -> Unit,
        onFavoriteClicked: (item: ProductPreviewItem.ProductPreview) -> Unit,
        onItemClicked: (item: ProductPreviewItem.ProductPreview) -> Unit
    ): Array<AdapterDelegate<List<ProductPreviewItem>>> = arrayOf(
        previewDelegate(onAddToCardClicked, onFavoriteClicked, onItemClicked),
        headerDelegate()
    )

    private fun previewDelegate(
        onAddToCardClicked: (item: ProductPreviewItem.ProductPreview) -> Unit,
        onFavoriteClicked: (item: ProductPreviewItem.ProductPreview) -> Unit,
        onItemClicked: (item: ProductPreviewItem.ProductPreview) -> Unit
    ): AdapterDelegate<List<ProductPreviewItem>> =
        adapterDelegateViewBinding<ProductPreviewItem.ProductPreview, _, _>(
            viewBinding = { inflater, parent ->
                ItemProductPreviewBinding.inflate(inflater, parent, false)
            }
        ) {
            with(binding) {
                val glide = com.bumptech.glide.Glide.with(itemView)

                addToCart.setOnClickListener {
                    onAddToCardClicked(item)
                    TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                }

                root.setOnClickListener {
                    onItemClicked(item)
                }

                fav.setOnClickListener {
                    onFavoriteClicked(item)
                }

                bind { payloads ->
                    if (!processPayload(binding, payloads)) {
                        setFav(fav, item.isFavorite)

                        setInCart(addToCart, item.isInCart)

                        setIsInProgress(addToCart, item.isInProgress)
                    }

                    price.setPrintableText(item.price)
                    name.setPrintableText(item.name)

                    rating.rating = item.rating.toFloat()

                    glide
                        .load(item.image)
                        .into(image)
                }
            }
        }

    private fun headerDelegate(): AdapterDelegate<List<ProductPreviewItem>> =
        adapterDelegateViewBinding<ProductPreviewItem.Header, _, _>(
            viewBinding = { inflater, parent ->
                ItemHeaderBinding.inflate(inflater, parent, false)
            }
        ) {
            with(binding) {
                bind {
                    title.setPrintableText(item.title)
                }
            }
        }

    private fun setFav(fav: ImageButton, favorite: Boolean) {
        fav.setImageResource(
            if (favorite) {
                com.srggrch.coreui.R.drawable.ic_favorite_20
            } else {
                com.srggrch.coreui.R.drawable.ic_favorite_border_20
            }
        )
    }

    private fun setInCart(addToCart: ProgressButton, isInCart: Boolean) {
        addToCart.text = if (isInCart) {
            "В корзине"
        } else {
            "+"
        }
    }

    private fun setIsInProgress(addToCart: ProgressButton, isInProgress: Boolean) {
        addToCart.inProgress = isInProgress
    }

    private fun processPayload(
        binding: ItemProductPreviewBinding,
        payload: List<Any>
    ): Boolean {
        if (payload.isEmpty()) return false

        payload.forEach {
            val p = it as Payload
            with(binding) {
                p.isFavorite?.let { isFavorite -> setFav(fav, isFavorite) }
                p.isInCart?.let { isInCart ->
                    TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                    setInCart(addToCart, isInCart)
                }
                p.isInProgress?.let { isInCart ->
                    TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                    setIsInProgress(addToCart, isInCart)
                }
            }
        }

        return true
    }

    data class Payload(
        val isFavorite: Boolean? = null,
        val isInCart: Boolean? = null,
        val isInProgress: Boolean? = null,
    )
}