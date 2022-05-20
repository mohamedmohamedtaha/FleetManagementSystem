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
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserLiveDataSource,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {
    var parameters = Parameters()

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

    private val _login = MutableLiveData<NetworkResult<Login>>()
    val login: LiveData<NetworkResult<Login>>
        get() = _login

    private val _request = MutableLiveData<NetworkResult<Any>>()
    val request: LiveData<NetworkResult<Any>>
        get() = _request

    fun request() {
        viewModelScope.launch {
            _request.postValue(NetworkResult.Loading())
            userRepository.request().collect { values ->
                _request.value = values
            }
        }
    }
    private val _subscribe = MutableLiveData<NetworkResult<Subscribe>>()
    val subscribe get() = _subscribe
    fun subscribe(){
        viewModelScope.launch {
            _subscribe.postValue(NetworkResult.Loading())
            userRepository.subscribe(parameters).collect{values ->
                _subscribe.value = values
            }
        }
    }

    private val _diseases = MutableLiveData<NetworkResult<List<Disease>>>()
    val diseases get() = _diseases
    fun getDiseases(){
        viewModelScope.launch {
            _diseases.postValue(NetworkResult.Loading())
            userRepository.getDiseases().collect{values->
                _diseases.value = values
            }
        }
    }
    private val _programDuration = MutableLiveData<NetworkResult<List<ProgramDuration>>>()
    val programDuration get() = _programDuration
    fun getProgramDuration(){
        viewModelScope.launch {
            _programDuration.postValue(NetworkResult.Loading())
            userRepository.getProgramDuration().collect{values->
                _programDuration.value = values
            }
        }
    }

    private val _dailyActivities = MutableLiveData<NetworkResult<List<DailyActivities>>>()
    val dailyActivities get() = _dailyActivities
    fun getDailyActivities(){
        viewModelScope.launch {
            _dailyActivities.postValue(NetworkResult.Loading())
            userRepository.getDailyActivities().collect{values->
                _dailyActivities.value=values
            }
        }
    }
private val _pricing = MutableLiveData<NetworkResult<Prices>>()
    val pricing get() = _pricing
    fun getPricing(){
        viewModelScope.launch {
            _pricing.postValue(NetworkResult.Loading())
            userRepository.getPricing(parameters).collect{values->
                _pricing.value = values
            }
        }
    }
    private val _changePassword = MutableLiveData<NetworkResult<Any>>()
    val changePassword: LiveData<NetworkResult<Any>>
        get() = _changePassword

    fun changePassword() {
        viewModelScope.launch {
            _changePassword.postValue(NetworkResult.Loading())
            userRepository.changePassword(parameters).collect { values ->
                _changePassword.value = values
            }
        }
    }

    private val _getProfile = MutableLiveData<NetworkResult<CreateAccount>>()
    val getProfile: LiveData<NetworkResult<CreateAccount>>
        get() = _getProfile

    fun getProfile() {

        viewModelScope.launch {
            _getProfile.postValue(NetworkResult.Loading())
            if (networkHelper.isNetworkConnected()) {
                userRepository.getProfile().collect { values ->
                    _getProfile.value = values
                }
            } else {
                _getProfile.postValue(NetworkResult.Error(0, "No internet connection"))
            }
        }
    }

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

    fun login(parameters: Parameters) {
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading())
            if (networkHelper.isNetworkConnected()){
            userRepository.login(parameters).collect { values ->
                _login.value = values
            }}else{
                _login.postValue(NetworkResult.Error(0, "No internet connection"))

            }
        }
    }

    private val _refresh = MutableLiveData<NetworkResult<Any>>()
    val refresh: LiveData<NetworkResult<Any>>
        get() = _refresh

    fun refresh(parameters: Parameters) {
        viewModelScope.launch {
            _refresh.postValue(NetworkResult.Loading())
            userRepository.refresh(parameters).collect { values ->
                _refresh.value = values
            }
        }
    }


    fun createAccount(parameters: Parameters) {
        viewModelScope.launch {
            _createAccount.postValue(NetworkResult.Loading())
            userRepository.createAccount(parameters).collect { values ->
                _createAccount.value = values
            }
        }
    }

    fun verificaitonCode(parameters: Parameters) {
        viewModelScope.launch {
            _verificaitonCode.postValue(NetworkResult.Loading())
            userRepository.verificationCode(parameters).collect { values ->
                _verificaitonCode.value = values
            }
        }
    }

    fun getSupplemntCategories() {
        viewModelScope.launch {
            _getSupplementCategories.postValue(NetworkResult.Loading())
            userRepository.getSupplementCategories().collect { values ->
                _getSupplementCategories.value = values
            }
        }
    }

    fun getSupplementById(params: Map<String, String>) {
        viewModelScope.launch {
            _getSupplementById.postValue(NetworkResult.Loading())
            userRepository.getSupplementById(params).collect { values ->
                _getSupplementById.value = values
            }
        }
    }

    fun forgetPassword() {
        viewModelScope.launch {
            _forgetPassword.postValue(NetworkResult.Loading())
            userRepository.forgetPassword(parameters).collect { values ->
                _forgetPassword.value = values
            }
        }
    }

    fun resetPassword() {
        viewModelScope.launch {
            _resetPassword.postValue(NetworkResult.Loading())
            userRepository.resetPassword(parameters).collect { values ->
                _resetPassword.value = values
            }
        }
    }
}