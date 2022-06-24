package com.srggrch.core.di

import com.srggrch.core.data.api.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {
    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)
}