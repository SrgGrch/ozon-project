package ru.ozon.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srggrch.core.data.models.Product
import com.srggrch.core.domain.cases.FavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ozon.coreui.PrintableText
import ru.ozon.details.R
import ru.ozon.details.domain.LoadDetailsUseCase
import ru.ozon.utils.MoneyFormatter
import ru.ozon.utils.data.Resource
import java.util.*

class ProductDetailsViewModel(
    private val loadDetailsUseCase: LoadDetailsUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading())
    internal val state: Flow<State> = _state.asStateFlow()

    fun fetchData(uuid: UUID) {
        viewModelScope.launch {
            _state.emit(
                when (val res = loadDetailsUseCase.execute(uuid)) {
                    is Resource.Error -> State.Error(res.data?.asItem())
                    is Resource.Loading -> State.Loading(res.data?.asItem())
                    is Resource.Success -> State.Data(res.data.asItem())
                }
            )
        }
    }

    internal sealed class State {
        abstract val product: ProductItem?

        data class Loading(override val product: ProductItem? = null) : State()
        data class Data(override val product: ProductItem) : State()
        data class Error(override val product: ProductItem? = null) : State()
    }

    private fun Product.asItem() = ProductItem(
        guid,
        PrintableText.Raw(name),
        PrintableText.Raw(MoneyFormatter.format(price)),
        PrintableText.Raw(description),
        rating,
        isFavorite,
        isInCart,
        images,
        count?.let { PrintableText.StringResource(R.string.details_count, it) },
        availableCount?.let { PrintableText.Raw(it.toString()) },
        buildList {
            addAll(additionalParams.map {
                ProductItem.AdditionalParam(
                    PrintableText.Raw(it.key),
                    PrintableText.Raw(it.value)
                )
            })

            weight?.let {
                add(
                    ProductItem.AdditionalParam(
                        PrintableText.StringResource(R.string.details_weight_title),
                        PrintableText.Raw(it.toString())
                    )
                )
            }
        }
    )

    fun onFavClicked() {
        val state = _state.value as? State.Data ?: return
        val product = state.product

        viewModelScope.launch {
            if (product.isFavorite) {
                favoriteUseCase.removeFromFavorite(product.guid)
            } else {
                favoriteUseCase.addToFavorite(product.guid)
            }

            _state.emit(state.copy(product = product.copy(isFavorite = !product.isFavorite)))
        }
    }
}