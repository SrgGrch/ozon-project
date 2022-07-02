package com.srggrch.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.srggrch.core.connectivity.ConnectivityService
import com.srggrch.core.connectivity.ConnectivityServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface ConnectivityModule {
    @Binds
    fun bindConnectivityService(impl: ConnectivityServiceImpl): ConnectivityService

    companion object {
        @Provides
        fun provideConnectivityManager(
            context: Context
        ): ConnectivityManager = context.getSystemService(ConnectivityManager::class.java)
    }
}