package com.srggrch.core.di

import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.services.ProductServiceImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ServiceModule {
    @Binds
    fun bindProductService(impl: ProductServiceImpl): ProductService
}