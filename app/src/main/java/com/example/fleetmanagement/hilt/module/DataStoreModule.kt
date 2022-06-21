package com.example.fleetmanagement.hilt.module

import android.content.Context
import com.example.fleetmanagement.data.datastore.DataStoreRepositoryImpl
import com.example.fleetmanagement.data.datastore.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun contextDataStoreProvider(@ApplicationContext context: Context): DataStoreRepository = DataStoreRepositoryImpl(context)
}