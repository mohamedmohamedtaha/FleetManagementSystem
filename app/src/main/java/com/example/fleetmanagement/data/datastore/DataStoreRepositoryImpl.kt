package com.example.fleetmanagement.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.fleetmanagement.data.datastore.Session.EMAIL
import com.example.fleetmanagement.data.datastore.Session.LANGUAGE
import com.example.fleetmanagement.data.datastore.Session.PREFERENCES_NAME
import com.example.fleetmanagement.data.datastore.Session.PASSWORD
import com.example.fleetmanagement.data.model.Login
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by
    preferencesDataStore(name = PREFERENCES_NAME)

    override suspend fun putLogin(email: String, password: String) {
        val preferencesKeyAccess = stringPreferencesKey(EMAIL)
        val preferencesKetRefresh = stringPreferencesKey(PASSWORD)
        context.dataStore.edit { preferences ->
            preferences[preferencesKeyAccess] = email
            preferences[preferencesKetRefresh] = password
        }
    }

    override  fun getLogin() = context.dataStore.data.map {
        val preferencesKeyAccess = stringPreferencesKey(EMAIL)
        val preferencesKetRefresh = stringPreferencesKey(PASSWORD)
        Login(it[preferencesKetRefresh] ?: "", it[preferencesKeyAccess] ?: "")
    }

    override suspend fun deleteLogin() = context.dataStore.edit {
        val preferencesKeyAccess = stringPreferencesKey(EMAIL)
        val preferencesKetRefresh = stringPreferencesKey(PASSWORD)
        Login(it.remove(preferencesKetRefresh), it.remove( preferencesKeyAccess))
    }

    override  fun getAccessLoginFlow() =
        context.dataStore.data.map { preferences->
            val preferencesKe = stringPreferencesKey(EMAIL)
            val preferencesKey = stringPreferencesKey(PASSWORD)
            preferences[preferencesKey]
            preferences[preferencesKe]
        }

    override suspend fun putAccess(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getAccess(): String? {
       return try {
           val preferencesKey = stringPreferencesKey(EMAIL)
           val preferences = context.dataStore.data.first()
           preferences[preferencesKey]
       }catch (e:Exception){
           e.printStackTrace()
           null
       }
    }


    override suspend fun putLanguage(language: String) {
       val preferencesKey = stringPreferencesKey(LANGUAGE)
        context.dataStore.edit { preferences->
            preferences[preferencesKey] = language
        }
    }
    override suspend fun getLanguage(): String? {
        return try {
            val preferencesKeyLanguage = stringPreferencesKey(LANGUAGE)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKeyLanguage]

        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferencesKey = intPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return try {
            val preferencesKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}














