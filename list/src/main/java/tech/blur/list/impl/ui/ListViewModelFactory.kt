package tech.blur.list.impl.ui

import androidx.lifecycle.SavedStateHandle
import tech.blur.core.domain.AddToCardUseCase
import tech.blur.core.domain.FavoriteUseCase
import tech.blur.core.domain.UpdatePreviewListUseCase
import tech.blur.list.impl.domain.GetProductPreviewUseCase
import tech.blur.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
    private val getProductPreviewUseCase: GetProductPreviewUseCase,
    private val updatePreviewList: UpdatePreviewListUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val addToCardUseCase: AddToCardUseCase
) : ViewModelAssistedFactory<ListViewModel> {
    override fun create(handle: SavedStateHandle): ListViewModel {
        return ListViewModel(
            getProductPreviewUseCase,
            updatePreviewList,
            favoriteUseCase,
            addToCardUseCase
        )
    }
}