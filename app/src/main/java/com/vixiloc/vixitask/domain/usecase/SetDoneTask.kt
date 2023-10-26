package com.vixiloc.vixitask.domain.usecase

import com.vixiloc.vixitask.domain.repository.TasksRepository

class SetDoneTask(private val repository: TasksRepository) {

    suspend operator fun invoke(id: Int) {
        repository.setDoneTask(id)
    }
}