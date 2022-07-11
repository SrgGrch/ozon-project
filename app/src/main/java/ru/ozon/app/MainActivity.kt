package ru.ozon.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ozon.app.databinding.ActivityMainBinding
import ru.ozon.utils.viewModels.GenericSavedStateViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private val vm: MainViewModel by viewModels {
        GenericSavedStateViewModelFactory(vmFactory, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        AndroidInjection.inject(this)

        vm.onCreate()

        vm.isNetworkAvailable
            .onEach {
                binding.noInternet.isVisible = !it
            }.launchIn(lifecycle.coroutineScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.onDestroy()
    }
}