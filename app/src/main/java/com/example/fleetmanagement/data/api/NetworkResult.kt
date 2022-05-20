package com.example.fleetmanagement.data.api

sealed class NetworkResult<T>(
    val code:Int? = null,
    val message:String?= null,val data:T? = null,){
    class Success<T>(code:Int,message:String,data: T):NetworkResult<T>(code,message,data)
    class Error<T>(code: Int,message: String):NetworkResult<T>(code,message)
    class Loading<T>: NetworkResult<T>()
}
