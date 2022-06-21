package com.example.fleetmanagement.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.fleetmanagement.data.datastore.Session.EMAIL
import com.example.fleetmanagement.data.datastore.Session.LANGUAGE
import com.example.fleetmanagement.data.datastore.Session.PASSWORD
import com.example.fleetmanagement.data.datastore.Session.PREFERENCES_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by
    preferencesDataStore(name = PREFERENCES_NAME)

    override suspend fun deleteEmail() = context.dataStore.edit {
        val preferencesKeyAccess = stringPreferencesKey(EMAIL)
        it.remove(preferencesKeyAccess)
    }

    override suspend fun deletePassword() = context.dataStore.edit {
        val preferencesKetRefresh = stringPreferencesKey(PASSWORD)
        it.remove(preferencesKetRefresh)
    }

    override suspend fun putEmail(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getEmail(): String? {
        return try {
            val preferencesKey = stringPreferencesKey(EMAIL)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun putPassword(value: String) {
        val preferencesKey = stringPreferencesKey(PASSWORD)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }

    }

    override suspend fun getPassword(): String? {
        return try {
            val preferencesKey = stringPreferencesKey(PASSWORD)
            val pereferences = context.dataStore.data.first()
            pereferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    override suspend fun putLanguage(language: String) {
        val preferencesKey = stringPreferencesKey(LANGUAGE)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = language
        }
    }

    override suspend fun getLanguage(): String? {
        return try {
            val preferencesKeyLanguage = stringPreferencesKey(LANGUAGE)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKeyLanguage]

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}














