package ru.ozon.addproduct.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.addproduct.R
import ru.ozon.addproduct.databinding.FragmentAddProductBinding
import ru.ozon.coreui.BaseBindingFragment
import javax.inject.Inject

class AddProductFragment :
    BaseBindingFragment<FragmentAddProductBinding>(R.layout.fragment_add_product) {
    override val viewBinding: FragmentAddProductBinding by viewBinding()

    @Inject
    lateinit var ui: AddProductUi
}