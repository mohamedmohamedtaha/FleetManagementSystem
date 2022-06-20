package com.example.fleetmanagement.data.api

import com.example.fleetmanagement.data.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.Query

interface UserRepository {
    suspend fun login( username:String,password:String, type:String): Flow<NetworkResult<LoginSuccess>>
    suspend fun getTasksList(@Query("Email") username:String, @Query("Password") password:String, @Query("type") type:String): Flow<NetworkResult<List<Task>>>

}