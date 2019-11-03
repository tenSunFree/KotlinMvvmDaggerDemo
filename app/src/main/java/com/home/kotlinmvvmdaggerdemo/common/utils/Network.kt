package com.home.kotlinmvvmdaggerdemo.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Network {

    companion object Utils {
        private fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        fun isConnected(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected
        }
    }
}

