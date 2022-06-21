package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.databinding.FragmentSubmitTicketBinding

class SubmitTicketFragment : BaseFragment() {
    private lateinit var binding: FragmentSubmitTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSubmitTicketBinding.inflate(layoutInflater)
        hideToolbar()
        return binding.root
    }

}