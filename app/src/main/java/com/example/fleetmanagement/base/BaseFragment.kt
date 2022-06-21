package com.example.fleetmanagement.base

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fleetmanagement.R
import com.example.fleetmanagement.data.datastore.DataStoreViewModel
import com.example.fleetmanagement.ui.home.viewmodel.LoginViewModel
import com.example.fleetmanagement.utils.UtilCountDownTimer
import com.example.fleetmanagement.utils.Validator
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var validator: Validator
    protected lateinit var toolbar: HasToolbar

    lateinit var utilTimer: UtilCountDownTimer
    val viewModel: LoginViewModel by viewModels()
    val dataStoreViewModel by viewModels<DataStoreViewModel>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is HasToolbar)
            toolbar = activity as HasToolbar
    }
    fun showProgressBar() {
        val progressBar = requireActivity().findViewById(R.id.progressBar) as View
        progressBar.visibility = View.VISIBLE
        progressBar.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.custom_progress_bar)
    }

    fun hideProgressBar() {
        val progressBar = requireActivity().findViewById(R.id.progressBar) as View
        progressBar.clearAnimation()
        progressBar.visibility = View.GONE
    }

    fun showHidePassword(checkBox: CheckBox, editText: TextInputEditText) {
        checkBox.setOnCheckedChangeListener { _, b ->
            if (b)
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            else
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun showToolbar() {
        toolbar.showToolbar(true)
        toolbar.showBackButton(false)
        toolbar.showProfileLogo(true)
        toolbar.showHomeLogo(true)
    }

    fun hideToolbar() {
        toolbar.showToolbar(true)
        toolbar.showBackButton(true)
        toolbar.showProfileLogo(false)
        toolbar.showHomeLogo(false)
    }
}