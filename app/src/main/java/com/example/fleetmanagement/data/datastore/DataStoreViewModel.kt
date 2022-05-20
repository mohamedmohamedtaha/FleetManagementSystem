package com.example.fleetmanagement.data.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleetmanagement.data.datastore.Session.NAME
import com.example.fleetmanagement.data.datastore.Session.PASSWORD
import com.example.fleetmanagement.data.model.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repository: DataStoreRepository) :
    ViewModel() {
    fun saveName(value: String) {
        viewModelScope.launch { repository.putString(NAME, value) }
    }

    fun getName(): String? = runBlocking { repository.getString(NAME) }


    fun getPassword(): String? = runBlocking { repository.getString(PASSWORD) }


    fun getAccess(): String? = runBlocking { repository.getAccess() }
    fun getLanguage(): String? = runBlocking { repository.getLanguage() }
    fun putLanguage(language: String) {
        viewModelScope.launch {
            repository.putLanguage(language)
        }

    }

    fun saveValue(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean("Bool", value)
        }
    }

    fun deleteLogin() = runBlocking { repository.deleteLogin() }

    fun getValue(): Boolean? = runBlocking { repository.getBoolean("Bool") }

    fun saveLoginData(login: Login) {
        viewModelScope.launch { repository.putLogin(login) }
    }

    val accessFlow: Flow<Login?>
        get() = repository.getLogin()
}