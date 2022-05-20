package com.example.fleetmanagement.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.fleetmanagement.R
import com.example.fleetmanagement.base.BaseActivity
import com.example.fleetmanagement.ui.home.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    @Inject
    lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    override fun onResume() {
        super.onResume()
        runnable = Runnable {
            Log.d("LoginFragment", " SplashFragment")
            startActivity(
                Intent(this, MainActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            )
            finish()
        }
        handler.postDelayed(runnable, 1250)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}