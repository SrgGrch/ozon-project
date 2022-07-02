package com.srggrch.core.connectivity

interface ConnectivityService {
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun removeAllObservers()

    fun interface Observer {
        fun onStateChanged(isAvailable: Boolean)
    }
}