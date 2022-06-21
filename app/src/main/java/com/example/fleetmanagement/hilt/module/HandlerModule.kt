package com.example.fleetmanagement.hilt.module

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HandlerModule {
    @Provides
    fun handlerProvider() = Handler(Looper.getMainLooper())


}