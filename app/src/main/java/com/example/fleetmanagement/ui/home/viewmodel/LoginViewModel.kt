package com.example.fleetmanagement.ui.home.viewmodel

import androidx.lifecycle.*
import com.example.fleetmanagement.data.api.NetworkHelper
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.api.UserLiveDataSource
import com.example.fleetmanagement.data.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLiveDataSource: UserLiveDataSource,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {


private val _sumLiveData=MutableLiveData<Int>()
    val sumLiveData get ()= _sumLiveData

    fun sum(number1: Int,number2:Int)  {
        _sumLiveData.value = number1 * number2
    }
    var parameters = Parameters()
    var email = ""
    var password = ""
    private val _login = MutableLiveData<NetworkResult<LoginSuccess>>()
    val login: LiveData<NetworkResult<LoginSuccess>>
        get() = _login

    fun login(username: String, password: String, type: String) {
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading())
            //if (networkHelper.isNetworkConnected()) {
                userLiveDataSource.login(username, password, type).collect { values ->
                    _login.value = values
                }
//            } else {
//               // _login.postValue(NetworkResult.Error( "No internet connection"))
//
//            }
        }
    }
        fun loginTest(username: String, password: String, type: String): LiveData<LoginSuccess> {
        return liveData(Dispatchers.IO) {
            when (val retrievedData = userLiveDataSource.login(username, password, type)) {
                is NetworkResult.Success<*> ->
                    emit(retrievedData.data as LoginSuccess)
                is NetworkResult.Error<*> ->
                    _login.postValue(retrievedData as NetworkResult<LoginSuccess>)
            }
        }
    }
    private val _getTasksList = MutableLiveData<NetworkResult<com.example.fleetmanagement.data.model.Task>>()
    val getTasksList get() = _getTasksList
    fun getTasksList(username: String, password: String, type: String) {
        viewModelScope.launch {
            _getTasksList.postValue(NetworkResult.Loading())
            if (networkHelper.isNetworkConnected()) {
                userLiveDataSource.getTasksList(username, password, type).collect { values ->
                    _getTasksList.value = values
                }
            } else {
                _getTasksList.postValue(NetworkResult.Error("No internet connection"))

            }
        }
    }
}