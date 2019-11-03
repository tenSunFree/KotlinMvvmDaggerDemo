package com.home.kotlinmvvmdaggerdemo.home.model.remote

import com.home.kotlinmvvmdaggerdemo.common.KMDDApplication
import com.home.kotlinmvvmdaggerdemo.common.service.ServiceGenerator
import com.home.kotlinmvvmdaggerdemo.common.utils.Constants
import com.home.kotlinmvvmdaggerdemo.common.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.home.kotlinmvvmdaggerdemo.common.utils.Network.Utils.isConnected
import com.home.kotlinmvvmdaggerdemo.home.model.remote.ResponseErrorModel.Companion.NETWORK_ERROR
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) {

    fun requestHomeData(): ResponseDataModel? {
        // isConnected(KMDDApplication.context) 檢查是否有網路
        return if (!isConnected(KMDDApplication.context)) {
            ResponseDataModel(
                ResponseErrorModel(
                    code = -1,
                    description = NETWORK_ERROR
                )
            )
        } else {
            val newsService =
                serviceGenerator.createService(HomeService::class.java, Constants.BASE_URL)
            processCall(newsService.fetchHomeData(), false)
        }
    }

    /**
     * @isVoid 用於僅使用代碼而沒有任何主體(例如某些Apis)進行答复的API, 回复200或401
     */
    private fun processCall(call: Call<*>, isVoid: Boolean): ResponseDataModel {
        if (!isConnected(KMDDApplication.context)) return ResponseDataModel(
            ResponseErrorModel()
        )
        try {
            // ?:之前如果為null, 那麼執行的就是?:後邊的邏輯, 後邊的具體邏輯由自己編寫, 不再限定為null
            val response = call.execute()
                ?: return ResponseDataModel(ResponseErrorModel(ERROR_UNDEFINED, NETWORK_ERROR))
            val responseCode = response.code() // HTTP status code
            // response.isSuccessful: 如果HTTP status code 是在200~300範圍內, 則返回true
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                ResponseDataModel(responseCode, apiResponse)
            } else {
                val serviceError = ResponseErrorModel(responseCode, response.message())
                ResponseDataModel(serviceError)
            }
        } catch (e: IOException) {
            return ResponseDataModel(ResponseErrorModel(ERROR_UNDEFINED, NETWORK_ERROR))
        }
    }
}
