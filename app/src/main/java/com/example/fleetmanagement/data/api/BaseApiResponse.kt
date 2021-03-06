package com.example.fleetmanagement.data.api

import android.util.Log
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> responseAPI(
        apiCall: suspend () -> T): NetworkResult<T> {
        try {
                val response = apiCall.invoke()
                        return NetworkResult.Success(response)
        } catch (e: Exception) {
            Log.d("LoginFragment", "${e.message}")
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
//    suspend fun <T> responseAPITwo(
//        apiCall: suspend () -> ResponseBody<T>): NetworkResult<T> {
//        try {
//                val response = apiCall()
//                if (response.code == 200) {
//                    val body = response.detail
//                    body?.let {
//                        return NetworkResult.Success(
//                            response.code,
//                            response.message,
//                            body
//                        )
//                    }
//                }
//                Log.d(
//                    "LoginFragment",
//                    "Error: ${response.code} ${response.message} ${response.detail}"
//                )
//                //  return NetworkResult.Success(response.detail!!)
//                return NetworkResult.Error(response.code, response.message)
//        } catch (e: Exception) {
//            Log.d("LoginFragment", "${e.message}")
//            return NetworkResult.Error(-1, e.message ?: e.toString())
//        }
//    }
}