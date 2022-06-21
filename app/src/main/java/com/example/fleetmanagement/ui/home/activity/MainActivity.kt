package com.example.fleetmanagement.ui.home.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fleetmanagement.base.BaseActivity
import com.example.fleetmanagement.base.HasToolbar
import com.example.fleetmanagement.ui.home.SelectionToolbar
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), HasToolbar {
    private lateinit var binding: ActivityMainBinding
    var selection: SelectionToolbar? = null
    private lateinit var navController: NavController
    //override fun findFragmentPlaceHolder() = R.id.frameLayoutMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)


        Log.d("LoginFragment", " MainActivity")
        // navigator.load(SplashFragment::class.java).add(true)
//
      //  setToolbar(binding.toolBar)




//        val mainFragment = MainFragment()
//        val transaction =
//            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, mainFragment)
//        transaction.commit()


        binding.includeToolBar.imageProfileUser.setOnClickListener{
            selection?.onSelectProfileImage(it)

        }

        //Set up an OnPreDrawListener
//        val content: View  = findViewById(android.R.id.content)
//
//        content.viewTreeObserver.addOnPreDrawListener(object :ViewTreeObserver.OnPreDrawListener {
//            override fun onPreDraw(): Boolean {
//                //Check if the initial data is ready
////                return if (viewModel.isReady) {
////                    // The content is ready; start drawing.
////                    content.viewTreeObserver.removeOnPreDrawListener(this)
////                    true
////                } else {
////                    // The content is not ready; suspend.
////                    false
////                }
//                return true
//            }
//
//        })
        //      hideBackButton()
//        hideLogo()
//        hideProfileImage()
//getBackButton().setOnClickListener { finish() }
    }
    fun setSelectionToolbar(callback :SelectionToolbar){
        selection = callback
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
        }else{
            binding.toolBar.navigationIcon = null
        }
        //        if (b)binding.includeToolBar.backButton.visibility = View.VISIBLE
//        else binding.includeToolBar.backButton.visibility = View.GONE
    }

    override fun showHomeLogo(b: Boolean) {
        if (b) binding.includeToolBar.logoImageToolBar.visibility = View.VISIBLE
        else binding.includeToolBar.logoImageToolBar.visibility = View.GONE
    }

    override fun showProfileLogo(b: Boolean) {
        if (b)binding.includeToolBar.imageProfileUser.visibility = View.VISIBLE
        else binding.includeToolBar.imageProfileUser.visibility = View.GONE
    }
}






















