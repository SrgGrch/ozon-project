package com.srggrch.core.data.storages

import android.util.Log
import androidx.room.Entity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srggrch.core.BuildConfig
import com.srggrch.core.data.models.Product
import com.srggrch.core.data.models.ProductPreview
import com.srggrch.core.data.sharedPreferences.SharedPreferencesProviderFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import java.util.*
import javax.inject.Inject

@Deprecated("Use ProductDetailsStorageImpl and ProductPreviewStorageImpl instead. They uses better storage solution")
class PreferencesProductDetailsStorage @Inject constructor(
    private val factory: SharedPreferencesProviderFactory
) : ProductDetailsStorage, ProductPreviewStorage {

    @Suppress("DEPRECATION")
    private val prefs by lazy {
        factory.create(PreferencesProductDetailsStorage::class.qualifiedName!!).provide()
    }

    companion object {
        private const val KEY_PRODUCTS = "products"
    }

    private val productsType: Type
        get() = object : TypeToken<List<ProductEntity>>() {}.type

    private val gson by lazy { Gson() }

    override suspend fun saveProducts(products: List<Product>): List<Product> {
        saveProducts(products.map { it.toEntity() })
        return products
    }

    override suspend fun saveProduct(product: Product): Product {
        val products = prefs.getString(KEY_PRODUCTS, null)?.fromJson()
            ?.let {
                it.filter { it.guid == product.guid }
            }

        if (products == null) {
            saveList(listOf(product.toEntity()))
        } else {
            saveList(products + product.toEntity())
        }

        return product
    }

    override suspend fun getAllProducts(): List<Product> {
        return getAllEntities().map { it.toProduct() }
    }

    override suspend fun findProduct(uuid: UUID): Product? {
        return getAllProducts().find { it.guid == uuid }
    }

    override suspend fun setFavorite(uuid: UUID, isFavorite: Boolean) {
        val products = getAllEntities().toMutableList()
        val index = getAllProducts().indexOfFirst { it.guid == uuid }

        if (index != -1) {
            products[index] = products[index].copy(isFavorite = isFavorite)
            saveList(products)
        }
    }

    override suspend fun increaseViewCount(uuid: UUID) {
        val products = getAllEntities().toMutableList()
        val index = getAllProducts().indexOfFirst { it.guid == uuid }

        if (index != -1) {
            products[index] = products[index].let { it.copy(viewsCount = it.viewsCount + 1) }
            saveList(products)
        }
    }

    override suspend fun saveProductsPreviews(products: List<ProductPreview>): List<ProductPreview> {
        saveProducts(products.map { it.toEntity() })

        return products
    }

    override suspend fun loadProductsPreviews(): List<ProductPreview> {
        return getAllEntities().map { it.toProductPreview() }
    }

    override fun loadProductsPreviewsFlow(): Flow<List<ProductPreview>> {
        @Suppress("UNREACHABLE_CODE")
        return if (BuildConfig.DEBUG) throw error("This class does not support proper flows")
        else flow {
            emit(loadProductsPreviews())
            Log.e(this::class.simpleName, "This class does not support proper flows")
        }
    }

    private fun saveProducts(products: List<ProductEntity>) {
        val storedList = getAllEntities().toMutableList()
        products
            .forEach { product ->
                val storedIndex = storedList.indexOfFirst { it.guid == product.guid }
                if (storedIndex == -1) {
                    storedList += product
                } else {
                    storedList[storedIndex] = storedList[storedIndex].copy(
                        guid = product.guid,
                        name = product.name,
                        price = product.price,
                        description = product.description,
                        rating = product.rating,
                        images = product.images,
                        weight = product.weight,
                        count = product.count,
                        availableCount = product.availableCount,
                        additionalParams = product.additionalParams
                    )
                }
            }

        saveList(storedList)
    }

    private fun getAllEntities(): List<ProductEntity> {
        return prefs.getString(KEY_PRODUCTS, null)?.fromJson() ?: listOf()
    }

    private fun saveList(list: List<ProductEntity>) {
        prefs.edit()
            .putString(KEY_PRODUCTS, gson.toJson(list))
            .apply()
    }

    private fun String.fromJson(): List<ProductEntity> = gson.fromJson(this, productsType)

    @Entity
    internal data class ProductEntity(
        val guid: UUID,
        val name: String,
        val price: String,
        val description: String? = null,
        val rating: Double,
        val isFavorite: Boolean,
        val isInCart: Boolean,
        val images: List<String>,
        val weight: Double? = null,
        val count: Int? = null,
        val availableCount: Int? = null,
        val additionalParams: Map<String, String> = emptyMap(),
        val viewsCount: Int = 0
    )

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

    private fun ProductEntity.toProduct() = Product(
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

    private fun ProductPreview.toEntity() = ProductEntity(
        guid = guid,
        name = name,
        images = listOf(image),
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart
    )

    private fun ProductEntity.toProductPreview() = ProductPreview(
        guid, images.first(), name, price, rating, isFavorite, isInCart
    )
}