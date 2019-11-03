package com.home.kotlinmvvmdaggerdemo.common.listeners

import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import com.home.kotlinmvvmdaggerdemo.home.model.remote.ResponseErrorModel

interface BaseCallback {

    fun onSuccess(homeModel: HomeBean)

    fun onFail(error: ResponseErrorModel?)
}
