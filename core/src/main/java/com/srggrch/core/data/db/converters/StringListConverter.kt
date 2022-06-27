package com.srggrch.core.data.db.converters

import androidx.room.TypeConverter

internal class StringListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun toString(strings: List<String>?): String? {
        return strings?.joinToString(",")
    }
}