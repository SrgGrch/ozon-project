package tech.blur.details.impl.ui

import androidx.lifecycle.SavedStateHandle
import tech.blur.core.domain.AddToCardUseCase
import tech.blur.core.domain.FavoriteUseCase
import tech.blur.details.impl.domain.LoadDetailsUseCase
import tech.blur.utils.viewModels.ViewModelAssistedFactory
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