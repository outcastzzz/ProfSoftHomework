package com.example.togetherApp.data.local.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDataDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val phone: String,
    val passwordHashed: String,
)
