package com.vixiloc.vixitask.presentations.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.data.model.Tasks
import com.vixiloc.vixitask.data.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenVm(private val taskRepository: TaskRepository) : ViewModel() {
    private val _visible = MutableStateFlow(false)
    val visible = _visible.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val  _tasks = MutableStateFlow<List<Tasks>>(emptyList())
    val tasks: Flow<List<Tasks>> = _tasks.asStateFlow()

    fun search(query:String) {
        _searchValue.update { query }
        viewModelScope.launch {
            taskRepository.search(searchValue.value).collect { tasks ->
                _tasks.value = tasks
            }
        }
    }

    fun done(id: Int) {
        viewModelScope.launch {
            taskRepository.setDoneTask(id)
        }
    }

    fun delete(tasks: Tasks) {

        viewModelScope.launch {
            taskRepository.deleteTask(tasks)
        }
    }

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collect { tasks ->
                _tasks.value = tasks
            }
        }
    }
}