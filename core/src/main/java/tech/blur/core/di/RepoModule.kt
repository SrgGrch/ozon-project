package tech.blur.core.di

import dagger.Binds
import dagger.Module
import tech.blur.core.data.repos.ProductDetailsRepository
import tech.blur.core.data.repos.ProductDetailsRepositoryImpl
import tech.blur.core.data.repos.ProductPreviewRepository
import tech.blur.core.data.repos.ProductPreviewRepositoryImpl

@Module
internal interface RepoModule {
    @Binds
    fun bindProductPreviewRepo(impl: ProductPreviewRepositoryImpl): ProductPreviewRepository

    @Binds
    fun bindProductDetailsRepo(impl: ProductDetailsRepositoryImpl): ProductDetailsRepository
}