package tech.blur.details.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.blur.core.data.models.Product
import tech.blur.core.domain.AddToCardUseCase
import tech.blur.core.domain.FavoriteUseCase
import tech.blur.coreui.PrintableText
import tech.blur.details.R
import tech.blur.details.impl.domain.LoadDetailsUseCase
import tech.blur.utils.MoneyFormatter
import tech.blur.utils.data.Resource
import java.util.*

class ProductDetailsViewModel(
    private val loadDetailsUseCase: LoadDetailsUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val addToCardUseCase: AddToCardUseCase
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
        if (isInCart) (1..100).random() else 0,// todo get from DB
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

    fun addToCart() {
        viewModelScope.launch {
            addToCardUseCase.addToCart(_state.value.product!!.guid)
            _state.emit(createNewStateWithCount(true, 1))
        }
    }

    fun changeCount(count: Int) {
        viewModelScope.launch {
            if (count == 0) {
                addToCardUseCase.removeFromCart(_state.value.product!!.guid)
                _state.emit(createNewStateWithCount(false))
            } else {
                _state.emit(createNewStateWithCount(true, count))
            }
        }
    }

    private fun createNewStateWithCount(isInCart: Boolean, count: Int = 0): State {
        return when (val state = _state.value) {
            is State.Data -> State.Data(
                state.product.copy(
                    isInCart = isInCart,
                    cartCount = count
                )
            )
            is State.Error -> State.Error(
                state.product?.copy(
                    isInCart = isInCart,
                    cartCount = count
                )
            )
            is State.Loading -> State.Loading(
                state.product?.copy(
                    isInCart = isInCart,
                    cartCount = count
                )
            )
        }
    }

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