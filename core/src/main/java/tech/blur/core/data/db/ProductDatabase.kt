package tech.blur.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.blur.core.data.db.converters.StringListConverter
import tech.blur.core.data.db.converters.StringMapConverter
import tech.blur.core.data.db.dao.ProductDao
import tech.blur.core.data.db.dao.ProductPreviewDao
import tech.blur.core.data.db.entities.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    version = 1
)
@TypeConverters(
    StringListConverter::class,
    StringMapConverter::class
)
internal abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getProductPreviewDao(): ProductPreviewDao
}