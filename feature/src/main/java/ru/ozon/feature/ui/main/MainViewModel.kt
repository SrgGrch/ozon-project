package ru.ozon.feature.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableSharedFlow<Int>().apply { this.tryEmit(1) }
    val state: SharedFlow<Int> = _state
}