package tech.blur.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.blur.core.data.db.entities.ProductEntity
import tech.blur.core.data.db.entities.ProductPreviewEntity
import java.util.*

@Dao
internal interface ProductPreviewDao {
    @Query("SELECT guid, name, images, price, rating, isFavorite, isInCart FROM ProductEntity")
    suspend fun getAllProductPreviews(): List<ProductPreviewEntity>

    @Query("SELECT guid, name, images, price,  rating, isFavorite, isInCart FROM ProductEntity")
    fun getAllProductPreviewsFlow(): Flow<List<ProductPreviewEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = ProductEntity::class)
    suspend fun insertAll(product: List<ProductPreviewEntity>)

    @Query(
        """
        UPDATE ProductEntity
        SET name         = :name,
        price            = :price,
        rating           = :rating,
        images           = :image
        WHERE guid = :guid
    """
    )
    suspend fun updateBaseInfo(
        guid: UUID,
        name: String,
        image: List<String>,
        price: String,
        rating: Double
    )
}