package tech.blur.addproduct.ui

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.blur.addproduct.R
import tech.blur.coreui.BaseUi
import javax.inject.Inject

class AddProductUi @Inject constructor(
    fragment: AddProductFragment,
    vmFactory: AddProductViewModelFactory
) : BaseUi<AddProductFragment>(fragment) {
    private val vm: AddProductViewModel by viewModels(vmFactory)

    private val viewBinding get() = fragment.viewBinding

    override fun onViewReady(view: View) {
        setupUi()
        vm.state
            .onEach(::renderState).launchIn(viewScope)
    }

    private fun setupUi() {
        with(viewBinding) {
            nameEditText.addTextChangedListener { e ->
                e?.let {
                    vm.onNameChanged(it.toString())
                }
            }

            descriptionEditText.addTextChangedListener { e ->
                e?.let {
                    vm.onDescriptionChanged(it.toString())
                }
            }

            priceEditText.addTextChangedListener { e ->
                e?.let {
                    vm.onPriceChanged(it.toString())
                }
            }

            imageEditText.addTextChangedListener { e ->
                e?.let {
                    vm.onImageChanged(it.toString())
                }
            }

            weightEditText.addTextChangedListener { e ->
                e?.let {
                    vm.onWeightChanged(it.toString())
                }
            }

            save.setOnClickListener {
                vm.save()
            }

            back.setOnClickListener {
                fragment.findNavController().popBackStack()

                //todo ask for confirmation
            }
        }
    }

    private fun renderState(state: AddProductViewModel.State) {
        state.validationResult.forEach(::setInputValidationResult)
        viewBinding.save.isEnabled = state.saveEnabled
        if (state.saved) fragment.findNavController().popBackStack()
    }

    private fun setInputValidationResult(result: AddProductViewModel.State.ValidationResult) {
        when (result.field) {
            AddProductViewModel.State.Field.Image -> {
                with(viewBinding.imageLayout) {
                    error =
                        if (!result.isValid) fragment.resources.getString(R.string.addProduct_image_error)
                        else null
                }
            }
            else -> Unit
        }
    }
}