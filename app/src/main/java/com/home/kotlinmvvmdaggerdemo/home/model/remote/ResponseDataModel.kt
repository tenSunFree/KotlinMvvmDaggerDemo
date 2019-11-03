package com.home.kotlinmvvmdaggerdemo.home.model.remote

class ResponseDataModel(var code: Int = 0, var error: ResponseErrorModel? = null, var data: Any? = null) {

    constructor(responseCode: Int, dataObject: Any?) : this(responseCode, data = dataObject)

    constructor(errorObject: ResponseErrorModel) : this(error = errorObject)
}
