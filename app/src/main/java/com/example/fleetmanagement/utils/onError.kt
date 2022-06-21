package com.example.fleetmanagement.utils

import android.content.Intent
import android.view.View
import com.example.fleetmanagement.data.model.LoginSuccess
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException

fun View.onError(throwable:Exception?) {
    try {
        if (throwable is RuntimeException) {
            when(throwable){
                is HttpException ->{
                    val gson = Gson()
                    val type = object : TypeToken<LoginSuccess>() {}.type
                    try {
                        val errorResponse: LoginSuccess? = gson.fromJson(throwable.response()?.errorBody()?.charStream(), type)
                        if (errorResponse != null) {
                            this.showSnackBar( errorResponse.message.toString())
                        }
                    } catch (ex: Exception) {
                        this.showSnackBar(ex.message.toString())
                    }
                }
            }
        } else if (throwable is ConnectException || throwable is SocketException) {
            this.showSnackBar("Connect to internet")
        }
        else if (throwable is SocketTimeoutException) {
            this.showSnackBar("Connection time out")
        }
        else {
            this.showSnackBar("Something wrong here")
            throwable?.printStackTrace()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        this.showSnackBar(throwable?.message.toString())
    }
}
