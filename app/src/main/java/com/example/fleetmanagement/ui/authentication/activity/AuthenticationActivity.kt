package com.example.fleetmanagement.ui.authentication.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.fleetmanagement.base.BaseActivity
import com.example.fleetmanagement.base.HasToolbar
import com.example.fleetmanagement.ui.authentication.fragment.LoginFragment
import com.example.fleetmanagement.utils.Navigator
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.ActivityAuthinticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity(), HasToolbar {
    private lateinit var binding: ActivityAuthinticationBinding
    // override fun findFragmentPlaceHolder() =  R.id.FrameLayoutAuthentication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthinticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("LoginFragment", " AuthenticationActivity")
//        hideLogo()
//        hideBackButton()
//        hideProfileImage()
        //navigator.load(LoginFragment::class.java).replace(true)
        val loginFragment = LoginFragment()
        Navigator.replace(loginFragment, this)
//
//        val splashFragment = LoginFragment()
//        val transaction =
//            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, splashFragment)
//        transaction.commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount - 1 == 0) {
            finish()
            Log.d("LoginFragment", " onBackPressed one ")
        } else {
            supportFragmentManager.popBackStack()
            Log.d("LoginFragment", " onBackPressed two ")

        }
        super.onBackPressed()

    }

    override fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    override fun getToolbar(): Toolbar {
        return binding.toolBar
    }

    override fun showToolbar(b: Boolean) {
        if (b) binding.toolBar.visibility = View.VISIBLE
        else binding.toolBar.visibility = View.GONE
    }

    override fun showBackButton(b: Boolean) {
        if (b) {
            binding.toolBar.navigationIcon =
                ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
            binding.toolBar.setNavigationOnClickListener { onBackPressed() }
        } else binding.toolBar.navigationIcon = null
    }

    override fun showHomeLogo(b: Boolean) {
        if (b) binding.includeToolBar.logoImageToolBar.visibility = View.VISIBLE
        else binding.includeToolBar.logoImageToolBar.visibility = View.GONE
    }

    override fun showProfileLogo(b: Boolean) {
        if (b) binding.includeToolBar.imageProfileUser.visibility = View.VISIBLE
        else binding.includeToolBar.imageProfileUser.visibility = View.GONE
    }
}