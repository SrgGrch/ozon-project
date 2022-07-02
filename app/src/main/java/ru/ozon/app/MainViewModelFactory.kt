package ru.ozon.app

import androidx.lifecycle.SavedStateHandle
import com.srggrch.core.connectivity.ConnectivityService
import ru.ozon.app.domain.cases.LoadDataUseCase
import ru.ozon.utils.viewModels.ViewModelAssistedFactory
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val connectivityService: ConnectivityService
) : ViewModelAssistedFactory<MainViewModel> {
    override fun create(handle: SavedStateHandle): MainViewModel {
        return MainViewModel(loadDataUseCase, connectivityService)
    }
}