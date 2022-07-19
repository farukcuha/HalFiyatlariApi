package com.pandorina.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

suspend fun <T> Flow<JsoupResult<T>>.collectJsoupResult(
    onSuccess: suspend (T?) -> Unit,
    onFailure: (Exception?) -> Unit){
    this.collectLatest { result ->
        when(result){
            is JsoupResult.Success -> { onSuccess(result.data) }
            is JsoupResult.Error -> { onFailure(result.e) }
        }
    }
}