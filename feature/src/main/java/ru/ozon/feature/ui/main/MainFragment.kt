package ru.ozon.feature.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.srggrch.utils.ui.viewScope
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ozon.feature.R
import ru.ozon.feature.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    private val viewBinding: FragmentMainBinding by viewBinding()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.onEach {
            viewBinding.message.text = it.toString()
        }.launchIn(viewScope)

        viewModel.state
    }
}