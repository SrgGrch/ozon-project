package ru.ozon.addproduct.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srggrch.core.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.ozon.addproduct.domain.SaveProductUseCaseImpl
import ru.ozon.addproduct.ui.validators.ImageUrlValidator
import java.util.*

class AddProductViewModel(
    private val imageUrlValidator: ImageUrlValidator,
    private val saveProductUseCaseImpl: SaveProductUseCaseImpl
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    internal val state: Flow<State> = _state.asStateFlow()
        .map { state ->
            val validationResult = state.image?.let {
                listOf(
                    State.ValidationResult(
                        State.Field.Image,
                        imageUrlValidator.validate(state.image)
                    )
                )
            }

            println(validationResult)

            state.copy(
                validationResult = validationResult ?: listOf(),
                saveEnabled = isSaveEnabled(state) && (validationResult != null && validationResult.all { it.isValid })
            )
        }

    fun onNameChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(name = value))
        }
    }

    fun onDescriptionChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(description = value))
        }
    }

    fun onPriceChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(price = value.toInt()))
        }
    }

    fun onImageChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(image = value))
        }
    }

    fun onWeightChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(weight = value.toDouble()))
        }
    }

    fun save() {
        viewModelScope.launch {
            saveProductUseCaseImpl.execute(_state.value.toProduct())
            _state.emit(_state.value.copy(saved = true))
        }
    }

    private fun isSaveEnabled(state: State): Boolean {
        return !state.name.isNullOrBlank()
                && !state.description.isNullOrBlank()
                && state.price != null
                && !state.image.isNullOrBlank()
    }

    private fun State.toProduct() = Product(
        UUID.randomUUID(),
        name = name!!,
        description = description!!,
        price = price!!.toString(),
        images = listOf(image!!),
        weight = weight,
        isFavorite = false,
        isInCart = false,
        availableCount = 0,
        count = 0,
        rating = 0.0,
        additionalParams = mapOf()
    )

    data class State(
        val name: String? = null,
        val description: String? = null,
        val price: Int? = null,
        val image: String? = null,
        val weight: Double? = null,
        val saved: Boolean = false,
        val validationResult: List<ValidationResult> = emptyList(),
        val saveEnabled: Boolean = false
    ) {
        data class ValidationResult(val field: Field, val isValid: Boolean)

        enum class Field {
            Name,
            Description,
            Price,
            Image,
            Weight
        }
    }
}