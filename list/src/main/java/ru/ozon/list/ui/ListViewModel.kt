package ru.ozon.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.domain.AddToCardUseCase
import com.srggrch.core.domain.FavoriteUseCase
import com.srggrch.core.domain.UpdatePreviewListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.ozon.coreui.PrintableText
import ru.ozon.list.R
import ru.ozon.list.domain.GetProductPreviewUseCase
import ru.ozon.utils.MoneyFormatter
import ru.ozon.utils.data.Resource
import kotlin.time.Duration.Companion.minutes

class ListViewModel(
    private val getProductPreviewUseCase: GetProductPreviewUseCase,
    private val updatePreviewList: UpdatePreviewListUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val addToCardUseCase: AddToCardUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading(emptyList()))
    internal val state: Flow<State> = _state.asStateFlow()

    private val job = SupervisorJob()
    private val updateScope = CoroutineScope(Dispatchers.IO + job)

    init {
        fetchData()
    }

    private fun fetchData() {
        getProductPreviewUseCase.execute()
            .onEach {
                val res = when (it) {
                    is Resource.Error -> State.Error(it.data?.toPrepareData() ?: emptyList())
                    is Resource.Loading -> State.Loading(it.data?.toPrepareData() ?: emptyList())
                    is Resource.Success -> State.Data(it.data.toPrepareData())
                }

                _state.emit(res)
            }.launchIn(viewModelScope)
    }

    fun onCreate() {
        updateScope.launch {
            while (true) {
                updatePreviewList.execute()
                delay(5.0.minutes)
            }
        }
    }

    internal fun addToFavorite(item: ProductPreviewItem.ProductPreview) {
        viewModelScope.launch {
            favoriteUseCase.addToFavorite(item.guid)
        }
    }

    internal fun removeFromFavorite(item: ProductPreviewItem.ProductPreview) {
        viewModelScope.launch {
            favoriteUseCase.removeFromFavorite(item.guid)
        }
    }

    internal fun onAddToCartClicked(item: ProductPreviewItem.ProductPreview) {
        viewModelScope.launch {
            val products = _state.value.products
            val index = products.indexOfFirst {
                (it as? ProductPreviewItem.ProductPreview)?.guid == item.guid
            }

            _state.value = _state.value.copyState(products = products.toMutableList().apply {
                val item = removeAt(index) as ProductPreviewItem.ProductPreview
                add(index, item.copy(isInProgress = true))
            })

            if (item.isInCart) {
                addToCardUseCase.removeFromCart(item.guid)
            } else {
                addToCardUseCase.addToCart(item.guid)
            }
        }
    }

    fun onDestroy() {
        job.cancelChildren()
    }

    internal sealed class State {
        abstract val products: List<ProductPreviewItem>

        data class Loading(override val products: List<ProductPreviewItem>) : State()
        data class Data(override val products: List<ProductPreviewItem>) : State()
        data class Error(override val products: List<ProductPreviewItem>) : State()

        fun copyState(products: List<ProductPreviewItem>): State = when (this) {
            is Data -> copy(products)
            is Error -> copy(products)
            is Loading -> copy(products)
        }
    }

    private fun List<ProductPreview>.toPrepareData(): List<ProductPreviewItem> {
        if (isEmpty()) return listOf()

        val firstPart = mutableListOf<ProductPreviewItem>().apply {
            add(
                0,
                ProductPreviewItem.Header(PrintableText.StringResource(R.string.list_header_under_hundred))
            )
        }

        val secondPart = mutableListOf<ProductPreviewItem>().apply {
            add(
                0,
                ProductPreviewItem.Header(PrintableText.StringResource(R.string.list_header_over_hundred))
            )
        }

        forEach {
            val part = if (it.price.toDouble() < 100) firstPart else secondPart
            part.add(it.toItem())
        }

        return firstPart + secondPart
    }

    private fun ProductPreview.toItem() = ProductPreviewItem.ProductPreview(
        guid,
        images,
        PrintableText.Raw(name),
        PrintableText.Raw(MoneyFormatter.format(price)),
        rating,
        isFavorite,
        isInCart
    )
}