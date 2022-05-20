package com.example.fleetmanagement.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.ui.home.activity.MainActivity
import com.example.fleetmanagement.ui.home.adapter.imageTransformSupplements
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (dataStoreViewModel.getAccess() != null){
//                binding.textViewLogIn.visibility = View.GONE
//                binding.textViewLogOut.visibility = View.VISIBLE
//                viewModel.getProfile()
//
//        }

//        dataStoreViewModel.accessFlow.asLiveData().observe(this, {
//            if (it?.access != null)
//                if (it.access.isNotEmpty()) {
//                    binding.textViewLogIn.visibility = View.GONE
//                    binding.textViewLogOut.visibility = View.VISIBLE
//                    viewModel.getProfile()
//                }
//        })

        viewModel.getProfile.observe(this, {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    if (it.data != null) {
                        binding.textViewNameUser.text = it.data.user?.userName
                        binding.textViewEmailUser.text = it.data.user?.email
                        imageTransformSupplements(
                            binding.imageProfileUser,
                            it.data.profileImage.toString()
                        )
                    }
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                    //   showSnackBar(it.message.toString())

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
        binding = FragmentProfileBinding.inflate(layoutInflater)
        binding.textViewLogIn.setOnClickListener(this)
        binding.textViewLogOut.setOnClickListener(this)
        binding.textViewSettingsClick.setOnClickListener(this)
        hideToolbar()
        if (dataStoreViewModel.getAccess() != null) {
            binding.textViewLogIn.visibility = View.GONE
            binding.textViewLogOut.visibility = View.VISIBLE
            viewModel.getProfile()
        }
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.textViewLogIn -> {
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            R.id.textViewLogOut -> {
                dataStoreViewModel.deleteLogin()
                startActivity(
                    Intent(requireActivity(), MainActivity::class.java).addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                    )
                )
                requireActivity().finish()
            }
            R.id.textViewSettingsClick -> {
                findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
            }
        }
    }
}