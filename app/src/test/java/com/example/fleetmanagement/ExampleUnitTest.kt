package com.example.fleetmanagement

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fleetmanagement.data.api.AuthenticationService
import com.example.fleetmanagement.data.api.NetworkHelper
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.api.UserLiveDataSource
import com.example.fleetmanagement.data.model.LoginSuccess
import com.example.fleetmanagement.ui.home.viewmodel.LoginViewModel
import com.example.fleetmanagement.utils.MainDispatcherRule
import com.example.fleetmanagement.utils.Validator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // For uses coroutines
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var networkHelper: NetworkHelper

    @Mock
    lateinit var authenticationService: AuthenticationService

    @Mock
    lateinit var userLiveDataSource: UserLiveDataSource

    @Mock
    lateinit var context : Context

    private val email = "hishamantar2000@gmail.com"
    private val rightPassword = "Test@123"
    private val wrongPassword = "error"
    private val type = "login"

    @Before
    fun setUp() {
        networkHelper = mock(NetworkHelper::class.java)
        authenticationService = mock(AuthenticationService::class.java)
        userLiveDataSource = mock(UserLiveDataSource::class.java)
        context = mock(Context::class.java)
    }
    @Test
    fun isEmailNotEmpty(){
        val validator = Validator()
        assertTrue(validator.submit("mohamed").checkEmpty()
            .errorMessage("").check())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkLoginSuccess() = runTest {
        val modelSuccess = LoginSuccess(1, "Login succesfully")
        val response = NetworkResult.Success(modelSuccess)
        val viewModel = LoginViewModel(userLiveDataSource, networkHelper)
        val channel = Channel<NetworkResult<LoginSuccess>>()
        val flow = channel.consumeAsFlow()
        Mockito.`when`(userLiveDataSource.login(email, rightPassword, type)).thenReturn(flow)

        launch { channel.send(response) }
        viewModel.login(email, rightPassword, type)
        advanceUntilIdle()
        assertEquals(response, viewModel.login.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkLoginFail() = runTest {
        val modelWrong = LoginSuccess(0, "error")
        val response = NetworkResult.Success(modelWrong)
        val viewModel = LoginViewModel(userLiveDataSource, networkHelper)
        val channel = Channel<NetworkResult<LoginSuccess>>()
        val flow = channel.consumeAsFlow()
        Mockito.`when`(userLiveDataSource.login(email, wrongPassword, type)).thenReturn(flow)

        launch { channel.send(response) }
        viewModel.login(email, wrongPassword, type)
        advanceUntilIdle()
        assertEquals(response, viewModel.login.value)
    }

    @Test
    fun checkSumMethod() {
        val viewModel = LoginViewModel(userLiveDataSource, networkHelper)
        viewModel.sumLiveData.observeForever { }
        viewModel.sum(10, 10)
        assertEquals(100, viewModel.sumLiveData.value)
    }

}
