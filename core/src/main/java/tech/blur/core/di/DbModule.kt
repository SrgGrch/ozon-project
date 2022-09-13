package tech.blur.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tech.blur.core.data.db.ProductDatabase
import tech.blur.core.data.db.dao.ProductDao
import tech.blur.core.data.db.dao.ProductPreviewDao
import javax.inject.Singleton

@Module
internal class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "ProductDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductsDao(db: ProductDatabase): ProductDao = db.getProductDao()

    @Provides
    fun provideProductPreviewDao(db: ProductDatabase): ProductPreviewDao = db.getProductPreviewDao()
}