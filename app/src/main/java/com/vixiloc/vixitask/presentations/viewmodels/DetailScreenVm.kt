package com.vixiloc.vixitask.presentations.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.data.model.Tasks
import com.vixiloc.vixitask.data.repositories.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenVm(private val taskRepository: TaskRepository) : ViewModel() {

    private val _task = MutableStateFlow<Tasks?>(null)
    val task = _task.asStateFlow()
    fun getTask(id: Int) {
        viewModelScope.launch {
            taskRepository.getTask(id).collect { task ->
                _task.value = task
            }
        }
    }
}