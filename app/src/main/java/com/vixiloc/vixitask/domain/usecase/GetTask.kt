package com.vixiloc.vixitask.domain.usecase

import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.repository.TasksRepository

class GetTask(private val repository: TasksRepository) {

    operator fun invoke(id: Int): Tasks {
        return repository.getTask(id)
    }
}