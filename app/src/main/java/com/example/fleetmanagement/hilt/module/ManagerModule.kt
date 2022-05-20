package com.example.fleetmanagement.hilt.module

import android.app.Activity
import androidx.fragment.app.FragmentManager
import com.example.fleetmanagement.base.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ManagerModule {
    @Provides
    fun baseActivityProvider(activity: Activity): BaseActivity {
        return activity as BaseActivity
    }

    @Provides
    fun fragmentManagerProvide(baseActivity: BaseActivity): FragmentManager {
        return baseActivity.supportFragmentManager
    }
//    @Provides
//    @Named("placeholder")
//    fun placeHolderProvide(baseActivity: BaseActivity):Int{
//        return baseActivity.findFragmentPlaceHolder()
//    }

}