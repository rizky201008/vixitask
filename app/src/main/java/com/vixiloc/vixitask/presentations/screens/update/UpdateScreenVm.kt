package com.vixiloc.vixitask.presentations.screens.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.usecase.GetTask
import com.vixiloc.vixitask.domain.usecase.UpdateTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdateScreenVm(
    val getTask: GetTask,
    val updateTask: UpdateTask
) : ViewModel() {
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

    fun getDetailTask(id: Int) {
        viewModelScope.launch {
            val task = getTask(id)
            _title.update { task.title }
            _date.update { task.date }
            _id.update { task.id!! }
            _active.update { task.isDone }
        }
    }

    fun update() {
        val task =
            Tasks(id = id.value, title = title.value, date = date.value, isDone = active.value)
        if (title.value.isNotBlank() && date.value.isNotBlank()) {
            _blank.update { false }
            viewModelScope.launch {
                updateTask(task)
            }
        } else {
            _blank.update { true }
        }
    }
}