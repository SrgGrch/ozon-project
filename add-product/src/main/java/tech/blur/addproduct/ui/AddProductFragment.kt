package tech.blur.addproduct.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import tech.blur.addproduct.R
import tech.blur.addproduct.databinding.FragmentAddProductBinding
import tech.blur.coreui.BaseBindingFragment
import javax.inject.Inject

class AddProductFragment :
    BaseBindingFragment<FragmentAddProductBinding>(R.layout.fragment_add_product) {
    override val viewBinding: FragmentAddProductBinding by viewBinding()

    @Inject
    lateinit var ui: AddProductUi
}