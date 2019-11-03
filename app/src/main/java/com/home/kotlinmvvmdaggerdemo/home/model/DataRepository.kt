package com.home.kotlinmvvmdaggerdemo.home.model

import com.home.kotlinmvvmdaggerdemo.home.model.remote.RemoteRepository
import com.home.kotlinmvvmdaggerdemo.home.model.remote.ResponseDataModel
import javax.inject.Inject

class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository)  {

    fun requestHomeData(): ResponseDataModel? {
        return remoteRepository.requestHomeData()
    }
}
