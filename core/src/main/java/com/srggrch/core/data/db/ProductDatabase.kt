package com.srggrch.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.srggrch.core.data.db.converters.StringListConverter
import com.srggrch.core.data.db.converters.StringMapConverter
import com.srggrch.core.data.db.dao.ProductDao
import com.srggrch.core.data.db.entities.ProductBaseEntity
import com.srggrch.core.data.db.entities.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        ProductBaseEntity::class
    ],
    version = 1
)
@TypeConverters(
    StringListConverter::class,
    StringMapConverter::class
)
internal abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}