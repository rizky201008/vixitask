package com.vixiloc.vixitask.presentations.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixitask.domain.model.Tasks
import com.vixiloc.vixitask.domain.usecase.GetTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenVm(val getTaskById: GetTask) : ViewModel() {

    private val _task = MutableStateFlow<Tasks?>(null)
    val task = _task.asStateFlow()
    fun getTask(id: Int) {
        viewModelScope.launch {
            _task.value = getTaskById(id)
        }
    }
}