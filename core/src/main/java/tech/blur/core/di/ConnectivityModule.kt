package tech.blur.core.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import tech.blur.core.connectivity.ConnectivityService
import tech.blur.core.connectivity.ConnectivityServiceImpl

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