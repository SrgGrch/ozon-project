package tech.blur.details.impl.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import tech.blur.coreui.BaseBindingFragment
import tech.blur.details.R
import tech.blur.details.databinding.FragmentDetailsBinding
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