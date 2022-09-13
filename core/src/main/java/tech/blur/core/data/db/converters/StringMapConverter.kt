package tech.blur.core.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class StringMapConverter {
    @TypeConverter
    fun fromString(value: String?): Map<String, String>? {
        return value?.let { Gson().fromJson(it, object : TypeToken<Map<String, Any>>() {}.type) }
    }

    @TypeConverter
    fun toString(map: Map<String, String>?): String? {
        return Gson().toJson(map)
    }
}