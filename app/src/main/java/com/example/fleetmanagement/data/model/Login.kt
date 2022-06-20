package com.example.fleetmanagement.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("refresh")
    @Expose
    val refresh: String,
    @SerializedName("access")
    @Expose
    val access: String)

class LoginSuccess(
    @SerializedName("success")
    @Expose
    val success: Int?,
    @SerializedName("message")
    @Expose
    val message: String? = null
)