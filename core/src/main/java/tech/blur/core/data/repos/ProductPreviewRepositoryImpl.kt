package tech.blur.core.data.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import tech.blur.core.data.models.ProductPreview
import tech.blur.core.data.services.ProductService
import tech.blur.core.data.storages.ProductPreviewStorage
import tech.blur.utils.data.Resource
import tech.blur.utils.data.doOnSuccess
import tech.blur.utils.data.mapDataToUnit
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