package com.vixiloc.vixitask.di

import com.vixiloc.vixitask.presentations.screens.add.AddScreenVm
import com.vixiloc.vixitask.presentations.screens.detail.DetailScreenVm
import com.vixiloc.vixitask.presentations.screens.home.HomeScreenVm
import com.vixiloc.vixitask.presentations.screens.splash.SplashScreenVm
import com.vixiloc.vixitask.presentations.screens.update.UpdateScreenVm
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