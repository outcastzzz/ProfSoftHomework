package com.example.togetherApp.data.local.database.converters

import androidx.room.TypeConverter
import com.example.togetherApp.data.local.database.models.common.ContentDbo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContentConverters {
    @TypeConverter
    fun fromContentDboList(value: List<ContentDbo>): String {
        val type = object : TypeToken<List<ContentDbo>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toContentDboList(value: String): List<ContentDbo> {
        val type = object : TypeToken<List<ContentDbo>>() {}.type
        return Gson().fromJson(value, type)
    }
}