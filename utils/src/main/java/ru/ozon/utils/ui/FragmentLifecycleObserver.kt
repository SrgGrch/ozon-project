package ru.ozon.utils.ui

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import androidx.fragment.app.Fragment as AndroidXFragment


abstract class FragmentLifecycleObserver<Fragment : AndroidXFragment>(
    protected val fragment: Fragment
) : DefaultLifecycleObserver {

    private val viewLifecycleObserver = getViewLifecycleObserver()
    private val viewLifecycleOwnerObserver = Observer<LifecycleOwner> {
        subscribeToViewLifecycle()
    }

    protected val viewScope: CoroutineScope get() = fragment.viewScope

    init {
        initialize()
    }

    private fun initialize() {
        fragment.lifecycle.addObserver(this)

        fragment.viewLifecycleOwnerLiveData.observe(fragment, viewLifecycleOwnerObserver)
    }

    override fun onCreate(owner: LifecycleOwner) {
        onCreate()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        onDestroy()
    }

    protected open fun onCreate() {}

    protected open fun onViewReady(view: View) {}

    protected open fun onStart(view: View) {}

    protected open fun onResume(view: View) {}

    protected open fun onPause(view: View) {}

    protected open fun onStop(view: View) {}

    protected open fun onViewDestroy() {}

    protected open fun onDestroy() {}

    protected fun stopObserveLifecycle() {
        fragment.lifecycle.removeObserver(this)
        fragment.viewLifecycleOwner.lifecycle.removeObserver(viewLifecycleObserver)
        fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerObserver)
    }

    private fun subscribeToViewLifecycle() {
        fragment.viewLifecycleOwner.lifecycle.addObserver(viewLifecycleObserver)
    }

    private fun getViewLifecycleObserver() = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            onViewReady(fragment.requireView())
        }

        override fun onDestroy(owner: LifecycleOwner) {
            onViewDestroy()
        }

        override fun onStart(owner: LifecycleOwner) {
            fragment.view?.let(::onStart)
        }

        override fun onResume(owner: LifecycleOwner) {
            fragment.view?.let(::onResume)
        }

        override fun onPause(owner: LifecycleOwner) {
            fragment.view?.let(::onPause)
        }

        override fun onStop(owner: LifecycleOwner) {
            fragment.view?.let(::onStop)
        }
    }
}


