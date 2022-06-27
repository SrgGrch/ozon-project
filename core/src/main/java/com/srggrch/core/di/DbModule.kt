package com.srggrch.core.di

import android.content.Context
import androidx.room.Room
import com.srggrch.core.data.db.ProductDatabase
import com.srggrch.core.data.db.dao.ProductDao
import dagger.Module
import dagger.Provides
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
}