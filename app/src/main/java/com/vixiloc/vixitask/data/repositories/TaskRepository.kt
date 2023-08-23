package com.vixiloc.vixitask.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.vixiloc.vixitask.data.dao.TaskDao
import com.vixiloc.vixitask.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

class TaskRepository(context: Context, private val taskDao: TaskDao) {
    fun getTasks(): Flow<List<Tasks>> {
        return taskDao.getAllTasks()
    }

    fun getTask(id: Int): Flow<Tasks> {
        return taskDao.getTask(id)
    }

    suspend fun createTask(task: Tasks) = taskDao.insert(task)

    suspend fun updateTask(task: Tasks) = taskDao.update(task)

    suspend fun setDoneTask(id: Int) = taskDao.setDoneTask(id)

    suspend fun deleteTask(task: Tasks) = taskDao.delete(task)

    fun search(query: String): Flow<List<Tasks>> {
        return taskDao.searchTasks("%$query%")
    }
}