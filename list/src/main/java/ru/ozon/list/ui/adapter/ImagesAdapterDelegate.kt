package ru.ozon.list.ui.adapter

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.details.databinding.ItemImageBinding

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