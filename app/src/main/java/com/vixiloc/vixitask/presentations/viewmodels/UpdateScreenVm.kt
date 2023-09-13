package com.vixiloc.vixitask.presentations.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.data.model.Tasks
import com.vixiloc.vixitask.data.repositories.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdateScreenVm(private val taskRepository: TaskRepository) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _active = MutableStateFlow(false)
    val active = _active.asStateFlow()

    private val _id = MutableStateFlow(0)
    val id = _id.asStateFlow()

    private val _blank = MutableStateFlow(true)
    val blank = _blank.asStateFlow()
    fun updateDate(value: String) = _date.update { value }
    fun updateTitle(value: String) = _title.update { value }

    fun getTask(id: Int) {
        viewModelScope.launch {
            taskRepository.getTask(id).collect { task ->
                _title.update { task.title }
                _date.update { task.date }
                _id.update { task.id!! }
                _active.update { task.isDone }
            }
        }
    }

    fun update() {
        val task =
            Tasks(id = id.value, title = title.value, date = date.value, isDone = active.value)
        if (title.value.isNotBlank() && date.value.isNotBlank()) {
            _blank.update { false }
            viewModelScope.launch {
                taskRepository.updateTask(task)
            }
        } else {
            _blank.update { true }
        }
    }
}