package com.vixiloc.vixitask.presentations.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.usecase.DeleteTask
import com.vixiloc.vixitask.domain.usecase.GetTasks
import com.vixiloc.vixitask.domain.usecase.SearchTasks
import com.vixiloc.vixitask.domain.usecase.SetDoneTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenVm(
    val searchTasks: SearchTasks,
    val setDoneTask: SetDoneTask,
    val deleteTask: DeleteTask,
    val getTasks: GetTasks
) : ViewModel() {
    private val _visible = MutableStateFlow(false)
    val visible = _visible.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _tasks = MutableStateFlow<List<Tasks>>(emptyList())
    val tasks: Flow<List<Tasks>> = _tasks.asStateFlow()

    fun search(query: String) {
        _searchValue.update { query }
        viewModelScope.launch {
            searchTasks(searchValue.value).collect { tasks ->
                _tasks.value = tasks
            }
        }
    }

    fun done(id: Int) {
        viewModelScope.launch {
            setDoneTask(id)
        }
    }

    fun delete(task: Tasks) {
        viewModelScope.launch {
            deleteTask(task)
        }
    }

    fun getAllTasks() {
        viewModelScope.launch {
            getTasks().collect { tasks ->
                _tasks.value = tasks
            }
        }
    }

    init {
        getAllTasks()
    }
}