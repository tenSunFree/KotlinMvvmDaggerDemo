package com.home.kotlinmvvmdaggerdemo.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class BaseViewModelFactory @Inject constructor(
    @JvmSuppressWildcards private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // ?:之前如果為null, 那麼執行的就是?:後邊的邏輯, 後邊的具體邏輯由自己編寫, 不再限定為null
        // firstOrNull() 返回與指定條件相符的第一個元素
        // 如果沒有找到這樣的元素或有找到多個這樣的元素, 則返回null
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
