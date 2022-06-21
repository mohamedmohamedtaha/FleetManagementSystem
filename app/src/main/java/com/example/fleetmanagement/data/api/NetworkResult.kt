package com.example.fleetmanagement.data.api

import java.lang.Exception

sealed class NetworkResult<T>{
    class Success<T>( val data: T):NetworkResult<T>()
    class Error<T>(val message: String):NetworkResult<T>()
    class ErrorEX<T>(val exception: Exception):NetworkResult<T>()

    class Loading<T>: NetworkResult<T>()
}
