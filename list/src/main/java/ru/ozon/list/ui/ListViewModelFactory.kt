package ru.ozon.list.ui

import androidx.lifecycle.SavedStateHandle
import com.srggrch.core.domain.FavoriteUseCase
import com.srggrch.core.domain.UpdatePreviewListUseCase
import ru.ozon.list.domain.GetProductPreviewUseCase
import ru.ozon.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
    private val getProductPreviewUseCase: GetProductPreviewUseCase,
    private val updatePreviewList: UpdatePreviewListUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModelAssistedFactory<ListViewModel> {
    override fun create(handle: SavedStateHandle): ListViewModel {
        return ListViewModel(getProductPreviewUseCase, updatePreviewList, favoriteUseCase)
    }
}