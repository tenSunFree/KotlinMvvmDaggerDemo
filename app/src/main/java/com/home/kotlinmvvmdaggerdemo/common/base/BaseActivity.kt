package com.home.kotlinmvvmdaggerdemo.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    protected abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeViewModel()
    }
}
