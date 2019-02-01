package com.batynchuk.ewave.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batynchuk.ewave.common.Workers
import kotlinx.coroutines.*
import java.lang.Exception

open class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {

    val exceptionLiveData = MutableLiveData<Exception>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    open fun postException(exception: Exception?) {
        exceptionLiveData.value = exception
        exceptionLiveData.value = null
    }
}



