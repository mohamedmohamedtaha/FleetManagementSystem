package com.example.fleetmanagement.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fleetmanagement.data.datastore.DataStoreViewModel
import com.example.fleetmanagement.utils.Constants.ARABIC
import com.example.fleetmanagement.utils.Constants.ENGLISH
import com.example.fleetmanagement.utils.Constants.LANGUAGE_ARABIC
import com.example.fleetmanagement.utils.Constants.LANGUAGE_ENGLISH
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    val dataStoreViewModel by viewModels<DataStoreViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguage()
    }
    private fun setLanguage(){
        val language :String

        if (dataStoreViewModel.getLanguage().isNullOrEmpty()){
            language = if (Resources.getSystem().configuration.locale.language == ARABIC)
            {
                dataStoreViewModel.putLanguage(LANGUAGE_ARABIC)
                ARABIC
            }else{
                dataStoreViewModel.putLanguage(LANGUAGE_ENGLISH)
                ENGLISH
            }
        }else{
           language = when(dataStoreViewModel.getLanguage()){
                LANGUAGE_ENGLISH ->  ENGLISH
                LANGUAGE_ARABIC -> ARABIC
                else ->{
                    dataStoreViewModel.putLanguage(LANGUAGE_ENGLISH)
                     ENGLISH
                }
            }
        }
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)
    }
//    //abstract fun findFragmentPlaceHolder(): Int
// //   abstract fun findContentView(): Int
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        super.setContentView(findContentView())
////
////    }
//    override fun setContentView(layoutResID: Int) {
//        binding = ActivityBaseBinding.inflate(layoutInflater)
//        super.setContentView(binding.root)
//    }
//
//    fun getBackButton(): AppCompatImageView {
//        return binding.includeToolBar.backButton
//    }
//
//    fun showBackButton() {
//        binding.includeToolBar.backButton.visibility = View.VISIBLE
//    }
//
//    fun hideBackButton() {
//        binding.includeToolBar.backButton.visibility = View.GONE
//    }
//
//    fun showLogo() {
//        binding.includeToolBar.logoImageToolBar.visibility = View.VISIBLE
//    }
//
//    fun hideLogo() {
//        binding.includeToolBar.logoImageToolBar.visibility = View.GONE
//    }
//    fun showProfileImage() {
//        binding.includeToolBar.imageProfileUser.visibility = View.VISIBLE
//    }
//
//    fun hideProfileImage() {
//        binding.includeToolBar.imageProfileUser.visibility = View.GONE
//    }

    }