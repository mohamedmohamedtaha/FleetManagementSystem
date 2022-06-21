package com.example.fleetmanagement.ui.authentication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.Parameters
import com.example.fleetmanagement.ui.home.activity.MainActivity
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.utils.Constants.EMAIL
import com.example.fleetmanagement.utils.TimerInterface
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentVerificationBinding
import com.example.fleetmanagement.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationFragment : BaseFragment(), View.OnClickListener, TimerInterface {
    private lateinit var binding: FragmentVerificationBinding
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVerificationBinding.inflate(layoutInflater)
        hideToolbar()
        binding.buttonConfirm.setOnClickListener(this)
      //  utilTimer = longFactory.create(this, 60, 1000)
        utilTimer.start()
        arguments?.let { email = it.getString(EMAIL).toString() }
        return binding.root
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
                validator.submit(binding.editTextVerificationCode)
            }.checkEmpty().errorMessage(getString(R.string.error_message_verification_code)).check()

            val parameters = Parameters()
            parameters.email = email
            parameters.OTP = binding.editTextVerificationCode.text.toString().trim()
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