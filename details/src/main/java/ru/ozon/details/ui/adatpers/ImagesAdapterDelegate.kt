package ru.ozon.details.ui.adatpers

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.coreui.setPrintableText
import ru.ozon.details.databinding.ItemAdditionalInfoBinding
import ru.ozon.details.databinding.ItemImageBinding
import ru.ozon.details.ui.ProductItem

internal object ImagesAdapterDelegate {
    operator fun invoke(): AdapterDelegate<List<String>> = adapterDelegateViewBinding(
        viewBinding = { inflater, parent ->
            ItemImageBinding.inflate(inflater, parent, false)
        }
    ) {
        val glide = Glide.with(binding.image)

        bind {
            with(binding) {
                glide
                    .load(item)
                    .into(image)
            }
        }
    }
}