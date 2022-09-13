package tech.blur.core.di

import dagger.Binds
import dagger.Module
import tech.blur.core.data.services.ProductService
import tech.blur.core.data.services.ProductServiceImpl

@Module
internal interface ServiceModule {
    @Binds
    fun bindProductService(impl: ProductServiceImpl): ProductService
}