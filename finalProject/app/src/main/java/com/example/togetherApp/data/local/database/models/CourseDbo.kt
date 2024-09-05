package com.example.togetherApp.data.local.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.togetherApp.data.local.database.models.common.ContentDbo

@Entity(tableName = "courses_table")
data class CourseDbo(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val text: List<ContentDbo>
)
