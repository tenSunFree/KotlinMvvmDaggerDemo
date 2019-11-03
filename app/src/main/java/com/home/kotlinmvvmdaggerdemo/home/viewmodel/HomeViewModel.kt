package com.home.kotlinmvvmdaggerdemo.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.kotlinmvvmdaggerdemo.common.listeners.BaseCallback
import com.home.kotlinmvvmdaggerdemo.home.model.DataRepository
import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import com.home.kotlinmvvmdaggerdemo.home.model.remote.ResponseDataModel
import com.home.kotlinmvvmdaggerdemo.home.model.remote.ResponseErrorModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    // MutableLiveData是方便我們使用的LiveData子類別, 提供setValue()和postValue()兩種方式更新value
    // 差異在於前者是在main thread執行, 若需要在background thread則改用後者
    var homeData: MutableLiveData<HomeBean> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<ResponseErrorModel> = MutableLiveData()

    fun getHomeData() {
        launch {
            try {
                // withContext(Dispatchers.IO): 跑在Worker thread上
                // withContext() 為我們提供了類似的編寫同步代碼的方式來實現異步編程
                val serviceResponse: ResponseDataModel? = withContext(Dispatchers.IO) {
                    Thread.sleep(2000) // 故意延遲, 展示ShimmerView的效果
                    dataRepository.requestHomeData()
                }
                if (serviceResponse?.code == ResponseErrorModel.SUCCESS_CODE) {
                    val data = serviceResponse.data
                    callback.onSuccess(data as HomeBean)
                } else {
                    callback.onFail(serviceResponse?.error)
                }
            } catch (e: Exception) {
                callback.onFail(ResponseErrorModel(e))
            }
        }
    }

    private val callback = object : BaseCallback {
        override fun onSuccess(homeModel: HomeBean) {
            this@HomeViewModel.homeData.postValue(homeModel)
        }

        override fun onFail(error: ResponseErrorModel?) {
            if (error?.code == -1) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }
        }
    }
}
