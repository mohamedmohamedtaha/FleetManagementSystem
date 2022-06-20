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

    //    private val _users = MutableLiveData<Resource<List<User>>>()
//    val users:LiveData<Resource<List<User>>>
//    get() = _users
    private val _users = MutableLiveData<NetworkResult<List<Gender>>>()
    val users: LiveData<NetworkResult<List<Gender>>>
        get() = _users

    private val _forgetPassword = MutableLiveData<NetworkResult<Any>>()
    val forgetPassword: LiveData<NetworkResult<Any>>
        get() = _forgetPassword

    private val _resetPassword = MutableLiveData<NetworkResult<Any>>()
    val resetPassword: LiveData<NetworkResult<Any>>
        get() = _resetPassword

    private val _login = MutableLiveData<NetworkResult<LoginSuccess>>()
    val login: LiveData<NetworkResult<LoginSuccess>>
        get() = _login

    private val _createAccount = MutableLiveData<NetworkResult<CreateAccount>>()
    val createAccount: LiveData<NetworkResult<CreateAccount>>
        get() = _createAccount

    private val _verificaitonCode = MutableLiveData<NetworkResult<Any>>()
    val verificationCode: LiveData<NetworkResult<Any>>
        get() = _verificaitonCode

    private val _getSupplementById = MutableLiveData<NetworkResult<List<Supplement>>>()
    val getSupplementById: LiveData<NetworkResult<List<Supplement>>>
        get() = _getSupplementById


    private val _getSupplementCategories =
        MutableLiveData<NetworkResult<List<SupplementCategories>>>()
    val getSupplementCategories: LiveData<NetworkResult<List<SupplementCategories>>>
        get() = _getSupplementCategories

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


    private val _getTasksList = MutableLiveData<NetworkResult<List<Task>>>()
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