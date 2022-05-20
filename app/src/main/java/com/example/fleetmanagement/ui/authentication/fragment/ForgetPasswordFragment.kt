package com.example.fleetmanagement.ui.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.forgetPassword.observe(this, {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    showSnackBar(it.data.toString())
                    val action =
                        ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToResetFragment(
                            viewModel.parameters.email.toString()
                        )
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                    showSnackBar(it.message.toString())
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        hideToolbar()
        binding.buttonSend.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonSend -> {
                validateValues()
            }
        }
    }

    private fun validateValues() {
        try {
            validator.apply {
                submit(binding.edieTextEmail).checkEmpty()
                    .errorMessage(getString(R.string.error_message_email)).checkEmail()
                    .errorMessage(getString(R.string.error_message_valid_email)).check()
            }
            viewModel.parameters.email = binding.edieTextEmail.text.toString().trim()
            viewModel.forgetPassword()
        } catch (e: ApplicationException) {

        }
    }

}