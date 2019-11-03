package com.home.kotlinmvvmdaggerdemo.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.home.kotlinmvvmdaggerdemo.common.di.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class KMDDApplication : MultiDexApplication(), HasActivityInjector {

    // Dagger2 Activity Injector
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}