package ru.ozon.list.ui

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

    fun fetchData() {
        repository.getProducts()
            .onEach {
                val res = when (it) {
                    is Resource.Error -> State.Error(it.data ?: emptyList())
                    is Resource.Loading -> State.Loading(it.data ?: emptyList())
                    is Resource.Success -> State.Data(it.data)
                }

                _state.emit(res)
            }.launchIn(viewModelScope)
    }

    sealed interface State {
        data class Loading(val products: List<ProductPreview>) : State
        data class Data(val products: List<ProductPreview>) : State
        data class Error(val products: List<ProductPreview>) : State
    }
}