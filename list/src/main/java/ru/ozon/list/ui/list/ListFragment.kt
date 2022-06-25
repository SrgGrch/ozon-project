package ru.ozon.list.ui.list

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.list.R
import ru.ozon.list.databinding.FragmentMainBinding
import ru.ozon.utils.ui.BaseBindingFragment
import javax.inject.Inject

class ListFragment : BaseBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    @Inject
    lateinit var listUi: ListUi

    override val viewBinding: FragmentMainBinding by viewBinding()
}