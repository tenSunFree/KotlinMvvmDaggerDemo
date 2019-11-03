package com.home.kotlinmvvmdaggerdemo.splash.view

import android.os.Bundle
import android.os.Handler
import com.home.kotlinmvvmdaggerdemo.R
import com.home.kotlinmvvmdaggerdemo.common.base.BaseActivity
import com.home.kotlinmvvmdaggerdemo.common.base.BaseViewModelFactory
import com.home.kotlinmvvmdaggerdemo.common.utils.Constants
import com.home.kotlinmvvmdaggerdemo.home.view.HomeActivity
import com.home.kotlinmvvmdaggerdemo.splash.viewmodel.SplashViewModel
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override val layoutId: Int get() = R.layout.activity_splash

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, Constants.SPLASH_DELAY.toLong())
    }
}
