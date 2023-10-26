package com.vixiloc.vixitask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vixiloc.vixitask.domain.model.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tasks: Tasks)

    @Update
    suspend fun update(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Query("SELECT * from tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Tasks>>

    @Query("UPDATE tasks SET `isDone` = 1 WHERE id = :id")
    suspend fun setDoneTask(id: Int)

    @Query("SELECT * FROM tasks WHERE `title` LIKE :query")
    fun searchTasks(query: String): Flow<List<Tasks>>

    @Query("SELECT * FROM tasks WHERE `id` = :id LIMIT 1")
    fun getTask(id: Int): Tasks
}