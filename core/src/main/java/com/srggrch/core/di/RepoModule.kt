package com.srggrch.core.di

import com.srggrch.core.data.repos.ProductRepository
import com.srggrch.core.data.repos.ProductRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface RepoModule {
    @Binds
    fun bindProductRepo(impl: ProductRepositoryImpl): ProductRepository
}