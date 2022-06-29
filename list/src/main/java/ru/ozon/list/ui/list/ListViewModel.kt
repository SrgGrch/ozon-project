package ru.ozon.list.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.domain.cases.FavoriteUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.ozon.list.domain.GetProductPreviewUseCase
import ru.ozon.list.domain.UpdatePreviewListUseCase
import ru.ozon.utils.MoneyFormatter
import ru.ozon.utils.data.Resource
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class ListViewModel @Inject constructor(
    private val getProductPreviewUseCase: GetProductPreviewUseCase,
    private val updatePreviewList: UpdatePreviewListUseCase,
    private val favoriteUseCase: FavoriteUseCase
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
                    is Resource.Error -> State.Error(it.data?.toItem() ?: emptyList())
                    is Resource.Loading -> State.Loading(it.data?.toItem() ?: emptyList())
                    is Resource.Success -> State.Data(it.data.toItem())
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

    internal fun addToFavorite(item: ProductPreviewItem) {
        viewModelScope.launch {
            favoriteUseCase.addToFavorite(item.guid)
        }
    }

    internal fun removeFromFavorite(item: ProductPreviewItem) {
        viewModelScope.launch {
            favoriteUseCase.removeFromFavorite(item.guid)
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
    }

    private fun List<ProductPreview>.toItem() = map {
        ProductPreviewItem(
            it.guid,
            it.image,
            it.name,
            MoneyFormatter.format(it.price),
            it.rating,
            it.isFavorite,
            it.isInCart
        )
    }
}