package com.vixiloc.vixitask.domain.usecase

import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(private val repository:TasksRepository) {

    operator fun invoke() : Flow<List<Tasks>> {
        return repository.getTasks()
    }
}