package com.example.fleetmanagement.data.api

import com.example.fleetmanagement.data.model.*
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(parameters: Parameters): Flow<NetworkResult<Login>>
    suspend fun forgetPassword(parameters: Parameters):Flow<NetworkResult<Any>>
    suspend fun changePassword(parameters: Parameters):Flow<NetworkResult<Any>>
    suspend fun getProfile():Flow<NetworkResult<CreateAccount>>
    suspend fun logOut(parameters: Parameters):Flow<NetworkResult<Any>>

    suspend fun request(): Flow<NetworkResult<Any>>
    suspend fun resetPassword(parameters: Parameters):Flow<NetworkResult<Any>>
    suspend fun refresh(parameters: Parameters):Flow<NetworkResult<Any>>
    suspend fun createAccount(parameters: Parameters): Flow<NetworkResult<CreateAccount>>
    suspend fun verificationCode(parameters: Parameters): Flow<NetworkResult<Any>>
    suspend fun getSupplementCategories(): Flow<NetworkResult<List<SupplementCategories>>>
    suspend fun getSupplementById(params: Map<String,String>): Flow<NetworkResult<List<Supplement>>>
    suspend fun subscribe(parameters: Parameters): Flow<NetworkResult<Subscribe>>
    suspend fun getDiseases(): Flow<NetworkResult<List<Disease>>>
    suspend fun getPricing(parameters: Parameters): Flow<NetworkResult<Prices>>
    suspend fun getProgramDuration(): Flow<NetworkResult<List<ProgramDuration>>>
    suspend fun getDailyActivities(): Flow<NetworkResult<List<DailyActivities>>>

}