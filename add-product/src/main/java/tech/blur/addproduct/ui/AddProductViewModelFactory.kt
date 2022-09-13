package tech.blur.addproduct.ui

import androidx.lifecycle.SavedStateHandle
import tech.blur.addproduct.domain.SaveProductUseCaseImpl
import tech.blur.addproduct.ui.validators.ImageUrlValidator
import tech.blur.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class AddProductViewModelFactory @Inject constructor(
    private val imageUrlValidator: ImageUrlValidator,
    private val saveProductUseCaseImpl: SaveProductUseCaseImpl
) : ViewModelAssistedFactory<AddProductViewModel> {
    override fun create(handle: SavedStateHandle): AddProductViewModel {
        return AddProductViewModel(imageUrlValidator, saveProductUseCaseImpl)
    }
}