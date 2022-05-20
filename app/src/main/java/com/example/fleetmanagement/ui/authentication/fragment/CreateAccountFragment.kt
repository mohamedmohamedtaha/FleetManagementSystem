package com.example.fleetmanagement.ui.authentication.fragment

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.Parameters
import com.example.fleetmanagement.utils.AppUtil
import com.example.fleetmanagement.utils.ApplicationException
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentCreateAccountBinding
    private var genderId = 1
    private val parameters = Parameters()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.createAccount.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    val action =
                        CreateAccountFragmentDirections.actionCreateAccountFragmentToVerificationFragment(
                            parameters.email.toString()
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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater)
        binding.buttonCreateAccount.setOnClickListener(this)
        hideToolbar()
        changeColorForConditions()
        checkRadioGroup()
        showHidePassword(binding.checkboxPassword, binding.editTextPasswordOne)
        showHidePassword(binding.checkboxPasswordTwo, binding.editTextPasswordTwo)
        return binding.root
    }

    private fun checkRadioGroup() {
        binding.radioGroupGender.setOnCheckedChangeListener { radioGroup, _ ->
            val selectId = radioGroup.checkedRadioButtonId
            if (selectId == R.id.radioButtonMale) {
                genderId = 1
            } else if (selectId == R.id.radioButtonFemale) {
                genderId = 2
            }
        }
    }

    private fun changeColorForConditions() {
        var spannableString = SpannableString(getString(R.string.label_agree_term_condition))
        AppUtil.apply {
            spannableString = getColorSpannable(
                requireContext(),
                getString(R.string.label_term_condition),
                spannableString,
                R.color.blue_primary
            )
            binding.checkBoxPrivacy.text = spannableString
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonCreateAccount -> {
                validateValues()

            }
        }
    }

    private fun validateValues() {
        try {
            validator.apply {
                validator.submit(binding.editTextFullName).checkEmpty()
                    .errorMessage(getString(R.string.error_message_name)).check()
                validator.submit(binding.editTextEmail).checkEmpty()
                    .errorMessage(getString(R.string.error_message_email)).checkEmail()
                    .errorMessage(getString(R.string.error_message_valid_email)).check()
                validator.submit(binding.editTextPasswordOne).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight)).check()
                validator.submit(binding.editTextPasswordTwo).checkEmpty()
                    .errorMessage(getString(R.string.error_message_password))
                    .checkMinLength(8)
                    .errorMessage(getString(R.string.error_message_min_length_eight))
                    .checkMatch(binding.editTextPasswordOne.text.toString())
                    .errorMessage(getString(R.string.error_message_password_not_match)).check()
            }
            if (!binding.checkBoxPrivacy.isChecked) {
                showSnackBar(getString(R.string.error_message_check_box))
                return
            }
            parameters.name = binding.editTextFullName.text.toString().trim()
            parameters.email = binding.editTextEmail.text.toString().trim()
            parameters.password1 = binding.editTextPasswordOne.text.toString().trim()
            parameters.password2 = binding.editTextPasswordTwo.text.toString().trim()
            parameters.genderId = genderId.toString()
            viewModel.createAccount(parameters)

        } catch (e: ApplicationException) {
        }
    }
}