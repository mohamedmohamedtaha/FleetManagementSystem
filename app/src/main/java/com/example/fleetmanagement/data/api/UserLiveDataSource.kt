package com.example.fleetmanagement.data.api

import com.example.fleetmanagement.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService, private val networkHelper: NetworkHelper) :
    UserRepository, BaseApiResponse() {

    override suspend fun login(parameters: Parameters): Flow<NetworkResult<Login>> {
        return flow{
            emit(responseAPI{authenticationService.login(parameters)})
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun forgetPassword(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.forgetPassword(parameters) }) }.flowOn(Dispatchers.IO
        )
    }

    override suspend fun changePassword(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.changePassword(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProfile(): Flow<NetworkResult<CreateAccount>> {
        return flow { emit(responseAPI { authenticationService.getProfile() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun logOut(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.logOut(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun request(): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.request() }) }.flowOn(Dispatchers.IO
        )
    }

    override suspend fun resetPassword(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.resetPassword(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun refresh(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow { emit(responseAPI { authenticationService.refresh(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun createAccount(parameters: Parameters): Flow<NetworkResult<CreateAccount>> {
        return flow {
            emit(responseAPI { authenticationService.createAccount(parameters) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun verificationCode(parameters: Parameters): Flow<NetworkResult<Any>> {
        return flow {
            emit(responseAPI { authenticationService.verificationCode(parameters) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSupplementCategories(): Flow<NetworkResult<List<SupplementCategories>>> {
        return flow {
            emit(responseAPI { authenticationService.getSupplementCategories() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSupplementById( params: Map<String,String>): Flow<NetworkResult<List<Supplement>>> {
        return flow {
            emit(responseAPI { authenticationService.getSupplementById(params) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun subscribe(parameters: Parameters): Flow<NetworkResult<Subscribe>> {
        return flow { emit(responseAPI { authenticationService.subscribe(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiseases(): Flow<NetworkResult<List<Disease>>> {
        return flow { emit(responseAPI { authenticationService.getDiseases() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPricing(parameters: Parameters): Flow<NetworkResult<Prices>> {
        return flow { emit(responseAPI { authenticationService.getPricing(parameters) }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProgramDuration(): Flow<NetworkResult<List<ProgramDuration>>> {
        return flow { emit(responseAPI { authenticationService.getProgramDuration() }) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDailyActivities(): Flow<NetworkResult<List<DailyActivities>>> {
        return flow { emit(responseAPI { authenticationService.getDailyActivity() }) }.flowOn(Dispatchers.IO)
    }


}