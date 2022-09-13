package tech.blur.coreui

import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope

val Fragment.viewScope get() = viewLifecycleOwner.lifecycle.coroutineScope