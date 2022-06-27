package ru.ozon.app

import ru.ozon.app.domain.cases.LoadDataUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) {
    fun onCreate() {
        loadDataUseCase.execute()
    }
}