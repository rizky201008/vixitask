package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.presentations.viewmodels.AddScreenVm
import com.vixiloc.vixitask.presentations.viewmodels.DetailScreenVm
import com.vixiloc.vixitask.presentations.viewmodels.HomeScreenVm
import com.vixiloc.vixitask.presentations.viewmodels.SplashScreenVm
import com.vixiloc.vixitask.presentations.viewmodels.UpdateScreenVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AddScreenVm(get())
    }

    viewModel {
        DetailScreenVm(get())
    }

    viewModel {
        HomeScreenVm(get())
    }

    viewModel {
        SplashScreenVm(get())
    }

    viewModel {
        UpdateScreenVm(get())
    }
}