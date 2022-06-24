package com.srggrch.core.di

import com.srggrch.core.data.storages.ProductPreviewStorage
import com.srggrch.core.data.storages.ProductPreviewStorageImpl
import com.srggrch.core.data.storages.ProductDetailsStorage
import com.srggrch.core.data.storages.ProductDetailsStorageImpl
import dagger.Binds
import dagger.Module

@Module
internal interface StorageModule {
    @Binds
    fun bindProductPreviewStorage(impl: ProductPreviewStorageImpl): ProductPreviewStorage

    @Binds
    fun bindProductsDetailsStorage(impl: ProductDetailsStorageImpl): ProductDetailsStorage
}