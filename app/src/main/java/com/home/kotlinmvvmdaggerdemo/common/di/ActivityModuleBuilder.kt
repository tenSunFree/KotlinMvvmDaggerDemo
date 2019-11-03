package com.home.kotlinmvvmdaggerdemo.common.di

import com.home.kotlinmvvmdaggerdemo.details.view.DetailsActivity
import com.home.kotlinmvvmdaggerdemo.home.view.HomeActivity
import com.home.kotlinmvvmdaggerdemo.splash.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): DetailsActivity
}
