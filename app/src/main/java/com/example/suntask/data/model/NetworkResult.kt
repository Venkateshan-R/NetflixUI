package com.example.suntask.data.model

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val code: Int,val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
}