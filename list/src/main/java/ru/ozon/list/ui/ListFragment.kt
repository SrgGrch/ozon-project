package ru.ozon.list.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.coreui.BaseBindingFragment
import ru.ozon.list.R
import ru.ozon.list.databinding.FragmentListBinding
import javax.inject.Inject

class ListFragment : BaseBindingFragment<FragmentListBinding>(R.layout.fragment_list) {
    @Inject
    lateinit var listUi: ListUi

    override val viewBinding: FragmentListBinding by viewBinding()
}