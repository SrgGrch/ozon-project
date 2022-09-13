package tech.blur.addproduct.ui.validators

internal interface Validator<T> {
    fun validate(value: T): Boolean
}