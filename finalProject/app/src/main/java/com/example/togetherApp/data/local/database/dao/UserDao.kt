package com.example.togetherApp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.togetherApp.data.local.database.models.UserDataDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserData(data: UserDataDbo)

    @Query("SELECT * FROM user_table")
    fun getUserData(): Flow<UserDataDbo>

    @Query("DELETE FROM user_table")
    suspend fun clearUserData()

}