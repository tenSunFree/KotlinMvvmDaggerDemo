package com.home.kotlinmvvmdaggerdemo.common.utils

class Constants {
    // companion: 可以理解為伴隨 伴生, 表示修飾的對象和外部類綁定, 一個類中最多只可以有一個伴生對象
    // 好處是調用的時候可以省掉對象名
    // object: 創建一個類, 並且創建一個這個類的對象, 實現的單例是一個餓漢式的單例, 並且實現了線程安全
    companion object INSTANCE {
        const val SPLASH_DELAY = 2000
        const val ERROR_UNDEFINED = -1
        const val BASE_URL = "https://data.taipei/opendata/datalist/"
        const val HOME_RECYCLER_VIEW_ITEM_KEY = "HOME_RECYCLER_VIEW_ITEM_KEY"
    }
}
