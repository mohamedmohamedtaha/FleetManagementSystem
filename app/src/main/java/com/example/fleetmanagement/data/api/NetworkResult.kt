package com.example.fleetmanagement.data.api

sealed class NetworkResult<T>{
    class Success<T>( val data: T):NetworkResult<T>()
    class Error<T>(message: String):NetworkResult<T>()
    class Loading<T>: NetworkResult<T>()
}
