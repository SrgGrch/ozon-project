package com.srggrch.core.connectivity

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ConnectivityServiceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectivityService {
    private val observers = mutableListOf<ConnectivityService.Observer>()
    private var lastAvailableState: Boolean? = null

    init {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                lastAvailableState = true
                observers.forEach {
                    it.onStateChanged(true)
                }
            }

            override fun onLost(network: Network) {
                lastAvailableState = false
                observers.forEach {
                    it.onStateChanged(false)
                }
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) = Unit

            override fun onLinkPropertiesChanged(
                network: Network,
                linkProperties: LinkProperties
            ) = Unit
        })
    }

    override fun addObserver(observer: ConnectivityService.Observer) {
        observers.add(observer)
        lastAvailableState?.let(observer::onStateChanged) ?: run {
            observer.onStateChanged(connectivityManager.activeNetwork != null)
        }
    }

    override fun removeObserver(observer: ConnectivityService.Observer) {
        observers.remove(observer)
    }

    override fun removeAllObservers() {
        observers.clear()
    }
}