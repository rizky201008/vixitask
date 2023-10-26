package com.vixiloc.vixitask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val date: String,
    val isDone: Boolean = false
)
