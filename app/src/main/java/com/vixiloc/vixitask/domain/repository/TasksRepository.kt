package com.vixiloc.vixitask.domain.repository

import com.vixiloc.vixitask.domain.model.Tasks
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTasks(): Flow<List<Tasks>>

    fun getTask(id: Int): Tasks

    suspend fun createTask(task: Tasks)

    suspend fun updateTask(task: Tasks)

    suspend fun setDoneTask(id: Int)

    suspend fun deleteTask(task: Tasks)

    fun searchTasks(query: String): Flow<List<Tasks>>
}