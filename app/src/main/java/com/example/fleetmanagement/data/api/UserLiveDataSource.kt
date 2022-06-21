package com.example.fleetmanagement.data.api

import com.example.fleetmanagement.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService, private val networkHelper: NetworkHelper) :
    UserRepository, BaseApiResponse() {
    override suspend fun login(
        username: String,
        password: String,
        type: String
    ): Flow<NetworkResult<LoginSuccess>> {
        return flow{
            emit(responseAPI{authenticationService.login(username,password,type)})
        }.flowOn(Dispatchers.IO)    }

    override suspend fun getTasksList(
        username: String,
        password: String,
        type: String
    ): Flow<NetworkResult<Task>> {
        return flow{
            emit(responseAPI{authenticationService.getTasksList(username,password,type)})
        }.flowOn(Dispatchers.IO)
    }


}