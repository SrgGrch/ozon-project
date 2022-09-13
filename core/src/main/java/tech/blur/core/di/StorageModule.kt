package tech.blur.core.di

import dagger.Binds
import dagger.Module
import tech.blur.core.data.storages.ProductDetailsStorage
import tech.blur.core.data.storages.ProductDetailsStorageImpl
import tech.blur.core.data.storages.ProductPreviewStorage
import tech.blur.core.data.storages.ProductPreviewStorageImpl

@Module
internal interface StorageModule {
    @Binds
    fun bindProductPreviewStorage(impl: ProductPreviewStorageImpl): ProductPreviewStorage

    @Binds
    fun bindProductsDetailsStorage(impl: ProductDetailsStorageImpl): ProductDetailsStorage
}