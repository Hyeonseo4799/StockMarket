package com.project.stockmarket.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
}

fun <T> safeFlow(apiFunc: suspend () -> T): Flow<NetworkResult<T>> = flow {
    try {
        val data = apiFunc.invoke()
        emit(NetworkResult.Success(data))
    } catch (e: HttpException) {
        emit(NetworkResult.Error("예기치 못한 오류"))
    } catch (e: IOException) {
        emit(NetworkResult.Error("네트워크 연결 오류"))
    }
}