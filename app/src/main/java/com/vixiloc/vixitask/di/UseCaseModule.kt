package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.domain.usecase.AddTask
import com.vixiloc.vixitask.domain.usecase.DeleteTask
import com.vixiloc.vixitask.domain.usecase.GetTask
import com.vixiloc.vixitask.domain.usecase.GetTasks
import com.vixiloc.vixitask.domain.usecase.SearchTasks
import com.vixiloc.vixitask.domain.usecase.SetDoneTask
import com.vixiloc.vixitask.domain.usecase.UpdateTask
import org.koin.dsl.module

val useCaseModule = module {
    single {
        AddTask(get())
    }
    single {
        DeleteTask(get())
    }
    single {
        GetTask(get())
    }
    single {
        GetTasks(get())
    }
    single {
        SearchTasks(get())
    }
    single {
        SetDoneTask(get())
    }
    single {
        UpdateTask(get())
    }
}