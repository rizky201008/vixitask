package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.data.repository.TasksRepositoryImpl
import com.vixiloc.vixitask.domain.repository.TasksRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TasksRepository> {
        TasksRepositoryImpl(get())
    }
}
