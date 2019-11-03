package com.home.kotlinmvvmdaggerdemo.common.di

import android.app.Application
import com.home.kotlinmvvmdaggerdemo.common.KMDDApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            ActivityModuleBuilder::class,
            ViewModelModule::class
        ]
)

interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: KMDDApplication)
}
