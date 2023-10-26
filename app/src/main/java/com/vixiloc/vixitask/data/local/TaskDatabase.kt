package com.vixiloc.vixitask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vixiloc.vixitask.domain.model.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                synchronized(TaskDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java, "task_db"
                    )
                        .build()
                }
            }
            return INSTANCE as TaskDatabase
        }
    }
}