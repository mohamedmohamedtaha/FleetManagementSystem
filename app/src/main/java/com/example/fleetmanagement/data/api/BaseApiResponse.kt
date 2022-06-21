package com.example.fleetmanagement.data.api

import android.util.Log
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> responseAPI(
        apiCall: suspend () -> T): NetworkResult<T> {
        return try {
            val response = apiCall.invoke()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            Log.d("LoginFragment", "${e.message}")
            NetworkResult.ErrorEX(e)
        }
    }
}