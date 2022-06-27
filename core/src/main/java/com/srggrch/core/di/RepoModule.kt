package com.srggrch.core.di

import com.srggrch.core.data.repos.ProductDetailsRepository
import com.srggrch.core.data.repos.ProductDetailsRepositoryImpl
import com.srggrch.core.data.repos.ProductPreviewRepository
import com.srggrch.core.data.repos.ProductPreviewRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface RepoModule {
    @Binds
    fun bindProductPreviewRepo(impl: ProductPreviewRepositoryImpl): ProductPreviewRepository

    @Binds
    fun bindProductDetailsRepo(impl: ProductDetailsRepositoryImpl): ProductDetailsRepository
}