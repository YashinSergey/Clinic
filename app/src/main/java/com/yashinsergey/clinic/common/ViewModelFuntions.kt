package com.yashinsergey.clinic.common

import androidx.lifecycle.*
import kotlinx.coroutines.launch

fun <A> ViewModel.launchInScope(f: suspend () -> A,
                                liveData: MutableLiveData<Result<A>>,
                                extraAction: ((A) -> Unit)? = null) =
    viewModelScope.launch { liveData.postValue(f, extraAction) }

suspend fun <A> MutableLiveData<Result<A>>.postValue(f: suspend () -> A, extraAction: ((A) -> Unit)? = null) {
    try {
        val a = f.invoke()
        extraAction?.invoke(a)
        postValue(Result.success(a))
    } catch (e: Throwable) {
        postValue(Result.failure(e))
    }
}
