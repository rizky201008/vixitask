package com.vixiloc.vixitask.domain.usecase

import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.repository.TasksRepository

class AddTask(private val repository: TasksRepository) {

    suspend operator fun invoke(task: Tasks) {
        repository.createTask(task)
    }
}