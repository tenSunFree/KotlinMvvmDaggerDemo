package com.home.kotlinmvvmdaggerdemo.common.di

import androidx.lifecycle.ViewModel
import com.home.kotlinmvvmdaggerdemo.details.viewmodel.DetailsViewModel
import com.home.kotlinmvvmdaggerdemo.home.viewmodel.HomeViewModel
import com.home.kotlinmvvmdaggerdemo.splash.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindUserViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun bindSplashViewModel(viewModel: DetailsViewModel): ViewModel
}
