package com.yash.jetpackcomposestates.utils

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import kotlin.coroutines.resumeWithException

suspend fun <T> Response<T>.await():T {
    return suspendCancellableCoroutine {cont->
        if(isSuccessful){
            cont.resume(body()!!,null)
        }else{
            cont.resumeWithException(Throwable(errorBody().toString()))
        }
    }
}