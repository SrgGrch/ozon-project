package ru.ozon.details.ui

import androidx.lifecycle.SavedStateHandle
import com.srggrch.core.domain.AddToCardUseCase
import com.srggrch.core.domain.FavoriteUseCase
import ru.ozon.details.domain.LoadDetailsUseCase
import ru.ozon.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class ProductDetailsViewModelFactory @Inject constructor(
    private val loadDetailsUseCase: LoadDetailsUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val addToCardUseCase: AddToCardUseCase
) : ViewModelAssistedFactory<ProductDetailsViewModel> {
    override fun create(handle: SavedStateHandle): ProductDetailsViewModel {
        return ProductDetailsViewModel(loadDetailsUseCase, favoriteUseCase, addToCardUseCase)
    }
}