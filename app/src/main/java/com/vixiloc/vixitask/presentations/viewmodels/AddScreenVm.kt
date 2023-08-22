package com.vixiloc.vixitask.presentations.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.data.model.Tasks
import com.vixiloc.vixitask.data.repositories.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddScreenVm(private val taskRepository: TaskRepository) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _blank = MutableStateFlow(true)
    val blank = _blank.asStateFlow()
    fun updateDate(value: String) = _date.update { value }
    fun updateTitle(value: String) = _title.update { value }

    fun save() {
        val task = Tasks(id = null, title = title.value, date = date.value)
        if (title.value.isNotBlank() && date.value.isNotBlank()) {
            _blank.update { false }
            viewModelScope.launch {
                taskRepository.createTask(task)
            }
        } else {
            _blank.update { true }
        }
    }
}