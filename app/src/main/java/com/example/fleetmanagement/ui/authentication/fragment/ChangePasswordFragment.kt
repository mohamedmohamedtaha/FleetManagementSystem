package com.example.fleetmanagement.ui.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.R
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.utils.TimerInterface
import com.example.fleetmanagement.databinding.FragmentChangePasswordBinding
import com.example.fleetmanagement.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment(), View.OnClickListener, TimerInterface {
    private lateinit var binding: FragmentChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(layoutInflater)
        hideToolbar()
        binding.buttonConfirm.setOnClickListener(this)
        binding.textViewResent.setOnClickListener(this)
        utilTimer = longFactory.create(this, 60, 1000)
        utilTimer.start()
        showHidePassword(binding.checkboxPassword, binding.editTextPassword)
        showHidePassword(binding.checkboxPasswordConfirmPassword, binding.editTextConfirmPassword)
        showHidePassword(binding.checkboxPasswordOldPassword, binding.editTextOldPassword)
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonConfirm -> {
                validateValues()
            }
            R.id.textViewResent -> {

            }

        }
    }

    private fun validateValues() {
        try {
            validator.apply {
                submit(binding.editTextVerificationCode).checkEmpty()
                    .errorMessage(getString(R.string.error_message_verification_code)).check()
                submit(binding.editTextOldPassword).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password)).check()
                submit(binding.editTextPassword).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight)).check()
                submit(binding.editTextConfirmPassword).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight))
                    .checkMatch(binding.editTextPassword.text.toString().trim())
                    .errorMessage(getString(R.string.error_message_password_not_match)).check()
            }
            viewModel.parameters.OTP = binding.editTextVerificationCode.text.toString().trim()
            viewModel.parameters.oldPassword = binding.editTextOldPassword.text.toString().trim()
            viewModel.parameters.newPassword1 = binding.editTextPassword.text.toString().trim()
            viewModel.parameters.newPassword2 =
                binding.editTextConfirmPassword.text.toString().trim()
        } catch (e: ApplicationException) {

        }
    }

    override fun start(millisUntilFinished: Long) {
        val value = millisUntilFinished / 1000
        binding.textViewTimer.text = value.toString()
    }

    override fun finish() {
        if (isAdded) {
            binding.textViewResent.isEnabled = true
            binding.textViewResent.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.blue_primary
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        utilTimer.stop()
    }


}