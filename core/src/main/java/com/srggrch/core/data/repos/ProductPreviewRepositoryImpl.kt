package com.srggrch.core.data.repos

import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.data.services.ProductService
import com.srggrch.core.data.storages.ProductPreviewStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.ozon.utils.data.Resource
import ru.ozon.utils.data.doOnSuccess
import ru.ozon.utils.data.mapDataToUnit
import javax.inject.Inject

internal class ProductPreviewRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productPreviewStorage: ProductPreviewStorage
) : ProductPreviewRepository {
    override suspend fun update(): Resource<Unit> {
        return productService.getProducts()
            .doOnSuccess {
                productPreviewStorage.saveProductsPreviews(it)
            }
            .mapDataToUnit()

    }

    override fun getProductsPreviews(): Flow<Resource<List<ProductPreview>>> {
        return productPreviewStorage.loadProductsPreviewsFlow()
            .map {
                Resource.newSuccess(it)
            }
            .onStart {
                update()
            }
    }
}