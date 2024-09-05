package com.example.togetherApp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.togetherApp.data.local.database.models.NoteDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getUserNotes(): Flow<List<NoteDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteDbo)

    @Query("DELETE FROM notes_table")
    suspend fun clearNotes()

}