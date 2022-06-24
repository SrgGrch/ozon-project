package ru.ozon.list.ui

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.utils.ui.viewScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ozon.list.R
import ru.ozon.list.databinding.FragmentMainBinding
import ru.ozon.utils.data.Resource
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var viewModel: ListViewModel

    private val viewBinding: FragmentMainBinding by viewBinding()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.onEach {
            viewBinding.message.text = it.toString()
            if (it is Resource.Error<*>) { it.desc.cause?.printStackTrace() }
        }.launchIn(viewScope)

        viewModel.state
    }
}