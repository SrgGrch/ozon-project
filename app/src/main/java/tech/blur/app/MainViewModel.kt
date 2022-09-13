package tech.blur.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.blur.app.domain.cases.LoadDataUseCase
import tech.blur.core.connectivity.ConnectivityService
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val connectivityService: ConnectivityService
) : ViewModel() {

    private val _isNetworkAvailable = MutableStateFlow<Boolean>(true)
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable.asStateFlow()

    private val observer = ConnectivityService.Observer {
        viewModelScope.launch {
            _isNetworkAvailable.emit(it)
        }
    }

    fun onCreate() {
        loadDataUseCase.execute()
        connectivityService.addObserver(observer)
    }

    fun onDestroy() {
        connectivityService.removeObserver(observer)
    }
}