package ru.ozon.addproduct.ui

import androidx.lifecycle.SavedStateHandle
import ru.ozon.addproduct.domain.SaveProductUseCaseImpl
import ru.ozon.addproduct.ui.validators.ImageUrlValidator
import ru.ozon.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class AddProductViewModelFactory @Inject constructor(
    private val imageUrlValidator: ImageUrlValidator,
    private val saveProductUseCaseImpl: SaveProductUseCaseImpl
) : ViewModelAssistedFactory<AddProductViewModel> {
    override fun create(handle: SavedStateHandle): AddProductViewModel {
        return AddProductViewModel(imageUrlValidator, saveProductUseCaseImpl)
    }
}