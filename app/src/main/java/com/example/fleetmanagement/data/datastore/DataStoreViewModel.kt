package com.example.fleetmanagement.data.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleetmanagement.data.datastore.Session.EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repository: DataStoreRepository) :
    ViewModel() {

    fun putPassword(password: String) {
        viewModelScope.launch { repository.putPassword(password) }
    }

    fun getPassword(): String? = runBlocking { repository.getPassword() }
    fun deletePassword() = runBlocking { repository.deletePassword() }

    fun putAccess(email: String) {
        viewModelScope.launch { repository.putEmail(EMAIL, email) }
    }

    fun getAccess(): String? = runBlocking { repository.getEmail() }
    fun deleteEmail() = runBlocking { repository.deleteEmail() }

    fun getLanguage(): String? = runBlocking { repository.getLanguage() }
    fun putLanguage(language: String) {
        viewModelScope.launch {
            repository.putLanguage(language)
        }

    }
}