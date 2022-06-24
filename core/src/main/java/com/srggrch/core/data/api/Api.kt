package com.srggrch.core.data.api

import com.srggrch.core.domain.models.Product
import com.srggrch.core.domain.models.ProductPreview
import retrofit2.http.GET

internal interface Api {
    @GET("v3/50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
    suspend fun getProducts(): List<ProductPreview>

    @GET("v3/8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
    suspend fun getProductDetails(): List<Product>
}