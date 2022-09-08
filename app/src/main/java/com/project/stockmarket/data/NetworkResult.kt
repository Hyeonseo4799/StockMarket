package com.project.stockmarket.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
}

fun <T> safeFlow(apiFunc: suspend () -> T): Flow<NetworkResult<T>> = flow {
    try {
        emit(NetworkResult.Success(apiFunc.invoke()))
    } catch (e: HttpException) {
        emit(NetworkResult.Error(e.localizedMessage ?: "An unexpected error occurred"))
    } catch (e: IOException) {
        emit(NetworkResult.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
    }
}