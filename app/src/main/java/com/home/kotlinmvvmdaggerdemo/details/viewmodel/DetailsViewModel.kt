package com.home.kotlinmvvmdaggerdemo.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import javax.inject.Inject

class DetailsViewModel @Inject
constructor() : ViewModel() {
    var result: MutableLiveData<HomeBean.Results.Result>? = MutableLiveData()
}
