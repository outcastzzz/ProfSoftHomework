package com.example.togetherApp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.togetherApp.data.local.database.converters.ContentConverters
import com.example.togetherApp.data.local.database.converters.NoteCommentConverters
import com.example.togetherApp.data.local.database.dao.NotesDao
import com.example.togetherApp.data.local.database.dao.UserDao
import com.example.togetherApp.data.local.database.models.NoteDbo
import com.example.togetherApp.data.local.database.models.UserDataDbo

@Database(
    entities = [UserDataDbo::class, NoteDbo::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(NoteCommentConverters::class, ContentConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun notesDao(): NotesDao

    companion object {

        private const val DB_NAME = "AppDatabase"
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = database
                return database
            }
        }
    }

}