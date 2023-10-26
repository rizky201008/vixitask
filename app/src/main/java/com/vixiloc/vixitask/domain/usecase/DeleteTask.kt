package com.vixiloc.vixitask.domain.usecase

import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.repository.TasksRepository

class DeleteTask(private val repository: TasksRepository) {

    suspend operator fun invoke(task: Tasks) {
        return repository.deleteTask(task)
    }
}