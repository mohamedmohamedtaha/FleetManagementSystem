package com.example.fleetmanagement.data.api
import com.example.fleetmanagement.data.model.*
import retrofit2.http.*

interface AuthenticationService {
    @GET("api")
    suspend fun login(@Query("Email") username:String, @Query("Password") password:String, @Query("type") type:String): LoginSuccess
    @GET("api")
    suspend fun getTasksList(@Query("Email") username:String, @Query("Password") password:String, @Query("type") type:String): Task
}