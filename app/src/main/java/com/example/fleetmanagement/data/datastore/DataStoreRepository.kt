package com.example.fleetmanagement.data.datastore

import androidx.datastore.preferences.core.Preferences
import com.example.fleetmanagement.data.model.Login
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun deleteEmail(): Preferences
    suspend fun deletePassword(): Preferences
    suspend fun putEmail(key:String, value:String)
    suspend fun getEmail():String?
    suspend fun putPassword(value:String)
    suspend fun getPassword():String?
    suspend fun getLanguage():String?
    suspend fun putLanguage(language:String)
}