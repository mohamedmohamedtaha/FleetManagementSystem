package com.example.fleetmanagement.ui.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.R
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.databinding.FragmentLoginBinding
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.login.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    binding.root.showSnackBar(it.data.message.toString())
                    if (it.data.success == 1){
//                        dataStoreViewModel.deleteLogin()
                        dataStoreViewModel.putAccess(viewModel.email)
                    dataStoreViewModel.saveLoginData(viewModel.email, viewModel.password)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)}
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                  //  binding.root.showSnackBar(it..message.toString())
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
            }
        }
//        viewModel.users.observe(this, Observer {
//            when(it){
//                is NetworkResult.Success ->{
//                    it.data?.forEach {gender ->
//                        Log.d("LoginFragment", "${gender.id}  ${gender.nameAr}")
//                    }}
//                is NetworkResult.Error ->{
//                    Log.d("LoginFragment", "ERROR")
//                }
//                is NetworkResult.Loading ->{
//                    Log.d("LoginFragment", "LOADING")
//                }
//
//            }
//
//        })
//        viewModel.users.observe(this, Observer {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    Log.d("LoginFragment", "SUCCESS")
//                }
//                Status.LOADING -> {
//                    Log.d("LoginFragment", "LOADING")
//
//                }
//                Status.ERROR -> {
//                    Log.d("LoginFragment", "ERROR")
//
//                }
//            }
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener(this)
        binding.textViewCreateAccount.setOnClickListener(this)
        binding.textViewForgetPassword.setOnClickListener(this)
        showHidePassword(binding.checkboxPassword, binding.editTextPassword)
        hideToolbar()
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.loginButton -> {
                validateValues()
            }
            R.id.textViewCreateAccount -> {
                findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
            }
            R.id.textViewForgetPassword -> {
                findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
        }
    }

    private fun getList() {
        //viewModel.login("list",,)

    }

    private fun validateValues() {
        try {
            validator.apply {
                validator.submit(binding.editTextEmail).checkEmpty()
                    .errorMessage(getString(R.string.error_message_email))
                    .checkEmail()
                    .errorMessage(getString(R.string.error_message_valid_email)).check()
                validator.submit(binding.editTextPassword).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .check()
            }
//            val parameters = Parameters()
//            parameters.type = "login"
            viewModel.email = binding.editTextEmail.text.toString().trim()
            viewModel.password = binding.editTextPassword.text.toString().trim()
            viewModel.login(viewModel.email, viewModel.password,"login")
        } catch (e: ApplicationException) {
            //showSnackBar(e.message)
        }
    }
}