package com.srggrch.core.data.api

import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import retrofit2.http.GET

internal interface Api {
    @GET("v3/ee6876a1-8c02-45aa-bde4-b91817a8b210")
    suspend fun getProducts(): List<ProductPreview>

    @GET("v3/d1b4763b-a5ea-471f-83bf-796da466e3d8")
    suspend fun getProductDetails(): List<Product>
}