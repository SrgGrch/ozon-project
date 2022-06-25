package ru.ozon.list.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srggrch.core.data.repos.ProductRepository
import com.srggrch.core.domain.models.ProductPreview
import kotlinx.coroutines.flow.*
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading(emptyList()))
    val state: Flow<State> = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        repository.getProducts()
            .onEach {
                val res = when (it) {
                    is Resource.Error -> State.Error(it.data?.toItem() ?: emptyList())
                    is Resource.Loading -> State.Loading(it.data?.toItem() ?: emptyList())
                    is Resource.Success -> State.Data(it.data.toItem())
                }

                _state.emit(res)
            }.launchIn(viewModelScope)
    }

    fun addToFavorite(item: ProductPreviewItem) {

    }

    fun removeFromFavorite(item: ProductPreviewItem) {

    }

    sealed class State {
        abstract val products: List<ProductPreviewItem>

        data class Loading(override val products: List<ProductPreviewItem>) : State()
        data class Data(override val products: List<ProductPreviewItem>) : State()
        data class Error(override val products: List<ProductPreviewItem>) : State()
    }

    private fun List<ProductPreview>.toItem() = map {
        ProductPreviewItem(
            it.guid,
            it.image,
            it.name,
            it.price,
            it.rating,
            it.isFavorite,
            it.isInCart
        )
    }
}