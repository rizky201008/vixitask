package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.data.repository.TasksRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        TasksRepositoryImpl(get())
    }
}
