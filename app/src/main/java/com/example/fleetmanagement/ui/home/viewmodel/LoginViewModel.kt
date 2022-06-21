package com.example.fleetmanagement.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleetmanagement.data.api.NetworkHelper
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.api.UserLiveDataSource
import com.example.fleetmanagement.data.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.concurrent.Task
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserLiveDataSource,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {
    var parameters = Parameters()
    var email = ""
    var password = ""
    private val _login = MutableLiveData<NetworkResult<LoginSuccess>>()
    val login: LiveData<NetworkResult<LoginSuccess>>
        get() = _login

    fun login(username: String, password: String, type: String) {
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading())
            if (networkHelper.isNetworkConnected()) {
                userRepository.login(username, password, type).collect { values ->
                    _login.value = values
                }
            } else {
                _login.postValue(NetworkResult.Error( "No internet connection"))

            }
        }
    }


    private val _getTasksList = MutableLiveData<NetworkResult<com.example.fleetmanagement.data.model.Task>>()
    val getTasksList get() = _getTasksList
    fun getTasksList(username: String, password: String, type: String) {
        viewModelScope.launch {
            _getTasksList.postValue(NetworkResult.Loading())
            if (networkHelper.isNetworkConnected()) {
                userRepository.getTasksList(username, password, type).collect { values ->
                    _getTasksList.value = values
                }
            } else {
                _getTasksList.postValue(NetworkResult.Error("No internet connection"))

            }
        }
    }
}