package tech.blur.app

import androidx.lifecycle.SavedStateHandle
import tech.blur.app.domain.cases.LoadDataUseCase
import tech.blur.core.connectivity.ConnectivityService
import tech.blur.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val connectivityService: ConnectivityService
) : ViewModelAssistedFactory<MainViewModel> {
    override fun create(handle: SavedStateHandle): MainViewModel {
        return MainViewModel(loadDataUseCase, connectivityService)
    }
}