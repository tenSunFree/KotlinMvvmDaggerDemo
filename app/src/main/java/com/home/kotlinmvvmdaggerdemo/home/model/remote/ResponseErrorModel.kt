package com.home.kotlinmvvmdaggerdemo.home.model.remote

class ResponseErrorModel {
    var description: String? = ""
    var code: Int = -1

    constructor()

    constructor(code: Int, description: String) {
        this.description = description
        this.code = code
    }

    constructor(exception: Exception) {
        this.description = exception.message
        this.code = 500
    }

    companion object {
        const val NETWORK_ERROR = "Unknown ResponseErrorModel"
        const val SUCCESS_CODE = 200
    }
}
