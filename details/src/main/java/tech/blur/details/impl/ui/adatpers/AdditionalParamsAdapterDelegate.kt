package tech.blur.details.impl.ui.adatpers

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import tech.blur.coreui.setPrintableText
import tech.blur.details.databinding.ItemAdditionalInfoBinding
import tech.blur.details.impl.ui.ProductItem

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