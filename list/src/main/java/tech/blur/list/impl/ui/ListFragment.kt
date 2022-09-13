package tech.blur.list.impl.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import tech.blur.coreui.BaseBindingFragment
import tech.blur.list.R
import tech.blur.list.databinding.FragmentListBinding
import javax.inject.Inject

class ListFragment : BaseBindingFragment<FragmentListBinding>(R.layout.fragment_list) {
    @Inject
    lateinit var listUi: ListUi

    override val viewBinding: FragmentListBinding by viewBinding()
}