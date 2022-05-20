package com.example.fleetmanagement.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("last_login")
    @Expose
    val lastLogin: String? = null,

    @SerializedName("username")
    @Expose
    val userName: String? = null,
    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("date_joined")
    @Expose
    val dateJoined: String? = null,

    @SerializedName("refresh")
    @Expose
    val refresh: String? = null,
    @SerializedName("access")
    @Expose
    val access: String? = null,
    @SerializedName("detail")
    @Expose
    val detail: String? = null


) {
}