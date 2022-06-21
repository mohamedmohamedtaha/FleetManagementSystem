package com.example.fleetmanagement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fleetmanagement.data.api.NetworkHelper
import com.example.fleetmanagement.data.api.UserLiveDataSource
import com.example.fleetmanagement.ui.home.viewmodel.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkApi() {

    }

}