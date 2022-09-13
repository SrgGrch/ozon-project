package tech.blur.core.data.storages

import tech.blur.core.data.db.dao.ProductDao
import tech.blur.core.data.db.entities.ProductEntity
import tech.blur.core.data.db.entities.toBaseEntity
import tech.blur.core.data.models.Product
import java.util.*
import javax.inject.Inject

internal class ProductDetailsStorageImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductDetailsStorage {
    override suspend fun saveProducts(products: List<Product>): List<Product> {
        val entities = products.map { it.toEntity() }
        productDao.insertAll(entities)
        productDao.updateBaseInfo(entities.map { it.toBaseEntity() })

        return products
    }

    override suspend fun saveProduct(product: Product): Product {
        val entity = product.toEntity()
        productDao.insert(entity)

        return product
    }


    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts().map { it.toDomainModel() }
    }

    override suspend fun findProduct(uuid: UUID): Product? {
        return productDao.findProduct(uuid)?.toDomainModel()
    }

    override suspend fun setFavorite(uuid: UUID, isFavorite: Boolean) {
        productDao.setFavorite(uuid, isFavorite)
    }

    override suspend fun increaseViewCount(uuid: UUID) {
        productDao.increaseViewCount(uuid)
    }

    override suspend fun setAddToCart(uuid: UUID, isInCart: Boolean) {
        productDao.setAddToCart(uuid, isInCart)
    }

    private fun Product.toEntity() = ProductEntity(
        guid,
        name,
        price,
        description,
        rating,
        isFavorite,
        isInCart,
        images,
        weight,
        count,
        availableCount,
        additionalParams
    )

    private fun ProductEntity.toDomainModel() = Product(
        guid,
        name,
        price,
        description.orEmpty(),
        rating,
        isFavorite,
        isInCart,
        images,
        weight,
        count,
        availableCount,
        additionalParams,
        viewsCount
    )
}