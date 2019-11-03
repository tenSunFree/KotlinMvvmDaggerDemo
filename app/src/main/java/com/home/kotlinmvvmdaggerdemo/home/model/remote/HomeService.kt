package com.home.kotlinmvvmdaggerdemo.home.model.remote

import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("apiAccess?scope=resourceAquire&rid=7a7a6b58-2814-4cb6-b15e-39bcb9090993")
    fun fetchHomeData(): Call<HomeBean>
}
