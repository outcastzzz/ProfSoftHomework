package com.example.togetherApp.data.local.database.converters

import androidx.room.TypeConverter
import com.example.togetherApp.data.local.database.models.NoteCommentDbo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteCommentConverters {
    @TypeConverter
    fun fromNoteCommentDboList(value: List<NoteCommentDbo>): String {
        val type = object : TypeToken<List<NoteCommentDbo>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toNoteCommentDboList(value: String): List<NoteCommentDbo> {
        val type = object : TypeToken<List<NoteCommentDbo>>() {}.type
        return Gson().fromJson(value, type)
    }

}