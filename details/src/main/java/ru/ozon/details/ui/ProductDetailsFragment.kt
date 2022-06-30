package ru.ozon.details.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.coreui.BaseBindingFragment
import ru.ozon.details.R
import ru.ozon.details.databinding.FragmentDetailsBinding
import javax.inject.Inject

/**
 * PDP fragment
 */
class ProductDetailsFragment :
    BaseBindingFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    override val viewBinding: FragmentDetailsBinding by viewBinding()

    @Inject
    lateinit var ui: ProductDetailsUi
}