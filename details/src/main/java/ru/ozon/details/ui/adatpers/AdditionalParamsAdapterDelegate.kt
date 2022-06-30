package ru.ozon.details.ui.adatpers

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.coreui.setPrintableText
import ru.ozon.details.databinding.ItemAdditionalInfoBinding
import ru.ozon.details.ui.ProductItem

internal object AdditionalParamsAdapterDelegate {
    operator fun invoke(): AdapterDelegate<List<ProductItem.AdditionalParam>> =
        adapterDelegateViewBinding(
            viewBinding = { inflater, parent ->
                ItemAdditionalInfoBinding.inflate(inflater, parent, false)
            }
        ) {
            bind {
                with(binding) {
                    title.setPrintableText(item.title)
                    value.setPrintableText(item.value)
                }
            }
        }
}