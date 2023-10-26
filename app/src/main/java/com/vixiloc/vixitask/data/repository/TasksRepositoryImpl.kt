package com.vixiloc.vixitask.data.repository

import com.vixiloc.vixitask.data.local.TaskDao
import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val taskDao: TaskDao) : TasksRepository {
    override fun getTasks(): Flow<List<Tasks>> {
        return taskDao.getAllTasks()
    }

    override fun getTask(id: Int): Tasks {
        return taskDao.getTask(id)
    }

    override suspend fun createTask(task: Tasks) {
        taskDao.insert(task)
    }

    override suspend fun updateTask(task: Tasks) {
        taskDao.update(task)
    }

    override suspend fun setDoneTask(id: Int) {
        taskDao.setDoneTask(id)
    }

    override suspend fun deleteTask(task: Tasks) {
        taskDao.delete(task)
    }

    override fun searchTasks(query: String): Flow<List<Tasks>> {
        return taskDao.searchTasks("%$query%")
    }
}