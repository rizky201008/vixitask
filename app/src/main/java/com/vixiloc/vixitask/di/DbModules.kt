package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.data.local.TaskDao
import com.vixiloc.vixitask.data.local.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dbModule = module {
    single {
        TaskDatabase.getDatabase(androidContext()).taskDao()
    }
}