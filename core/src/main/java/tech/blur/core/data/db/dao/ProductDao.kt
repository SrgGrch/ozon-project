package tech.blur.core.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.db.entities.ProductBaseEntity
import tech.blur.core.data.db.entities.ProductEntity
import java.util.*

@Dao
internal interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE guid = :uuid")
    suspend fun findProduct(uuid: UUID): ProductEntity?

    @Query("SELECT * FROM ProductEntity")
    fun getAllProductsFlow(): Flow<List<ProductEntity>>

    @Query("UPDATE ProductEntity SET isFavorite = :isFavorite WHERE guid = :uuid")
    suspend fun setFavorite(uuid: UUID, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductEntity)

    @Update(entity = ProductEntity::class)
    suspend fun updateBaseInfo(
        products: List<ProductBaseEntity>
    )

    @Query("UPDATE ProductEntity SET viewsCount = viewsCount + 1 WHERE guid = :uuid")
    fun increaseViewCount(uuid: UUID)

    @Query("UPDATE ProductEntity SET isInCart = :isInCart WHERE guid = :uuid")
    fun setAddToCart(uuid: UUID, isInCart: Boolean)
}