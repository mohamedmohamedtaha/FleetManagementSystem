package com.example.fleetmanagement.data.datastore

import androidx.datastore.preferences.core.Preferences
import com.example.fleetmanagement.data.model.Login
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun putLogin(email: String,password:String)
    fun getLogin(): Flow<Login>
    suspend fun deleteLogin(): Preferences
    fun getAccessLoginFlow(): Flow<String?>
    suspend fun putAccess(key:String,value:String)
    suspend fun getAccess():String?
    suspend fun getLanguage():String?
    suspend fun putLanguage(language:String)
    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getString(key: String): String?
    suspend fun getInt(key: String): Int?
    suspend fun getBoolean(key: String): Boolean?


}