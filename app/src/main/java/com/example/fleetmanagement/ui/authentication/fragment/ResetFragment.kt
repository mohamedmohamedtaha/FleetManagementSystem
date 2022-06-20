package com.example.fleetmanagement.ui.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.utils.Constants.EMAIL
import com.example.fleetmanagement.utils.TimerInterface
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentResetBinding
import com.example.fleetmanagement.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetFragment : BaseFragment(), View.OnClickListener, TimerInterface {
    private lateinit var binding: FragmentResetBinding
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.resetPassword.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    binding.root.showSnackBar(it.data.toString())
                    findNavController().navigate(R.id.action_resetFragment_to_loginFragment)
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                  //  binding.root.showSnackBar(it.message.toString())
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResetBinding.inflate(layoutInflater)
        hideToolbar()
        binding.buttonConfirm.setOnClickListener(this)
        utilTimer = longFactory.create(this, 60, 1000)
        utilTimer.start()
        arguments?.let { email = it.getString(EMAIL).toString() }
        showHidePassword(binding.checkboxPassword, binding.editTextPasswordOne)
        showHidePassword(binding.checkboxConfirmPassword, binding.editTextPasswordTwo)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        utilTimer.stop()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonConfirm -> {
                validateValues()
            }
        }
    }

    private fun validateValues() {
        try {
            validator.apply {
                validator.submit(binding.editTextVerificationCode).checkEmpty()
                    .errorMessage(getString(R.string.verification_code)).check()
                validator.submit(binding.editTextPasswordOne).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight)).check()
                validator.submit(binding.editTextPasswordTwo).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password)).checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight))
                    .checkMatch(binding.editTextPasswordOne.text.toString())
                    .errorMessage(getString(R.string.error_message_password_not_match)).check()
            }
            viewModel.parameters.email = email
            viewModel.parameters.OTP = binding.editTextVerificationCode.text.toString().trim()
            viewModel.parameters.newPassword1 = binding.editTextPasswordOne.text.toString().trim()
            viewModel.parameters.newPassword2 = binding.editTextPasswordTwo.text.toString().trim()
        } catch (e: ApplicationException) {

        }
    }

    override fun start(millisUntilFinished: Long) {
        val value = millisUntilFinished / 1000
        binding.textViewTimer.text = value.toString()
    }

    override fun finish() {
        binding.textViewResent.isEnabled = true
        binding.textViewResent.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue_primary
            )
        )
    }
}