package com.pandorina.data.remote

sealed class JsoupResult<T>(val data: T? = null, val e: Exception? = null){
    class Success<T>(data: T?) : JsoupResult<T>(data = data)
    class Error<T>(e: Exception?) : JsoupResult<T>(e = e)
}