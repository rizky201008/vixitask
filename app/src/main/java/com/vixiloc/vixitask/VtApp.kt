package com.vixiloc.vixitask

import android.app.Application
import com.vixiloc.vixitask.di.dbModule
import com.vixiloc.vixitask.di.repositoryModule
import com.vixiloc.vixitask.di.useCaseModule
import com.vixiloc.vixitask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VtApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VtApp)
            modules(listOf(viewModelModule, repositoryModule, dbModule, useCaseModule))
        }
    }
}