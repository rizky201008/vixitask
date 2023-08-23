package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.data.repositories.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        TaskRepository(context = androidContext(), taskDao = get())
    }
}
