package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fleetmanagement.databinding.FragmentCountCaloriesBinding

class CountCaloriesFragment : Fragment() {
    private lateinit var binding: FragmentCountCaloriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCountCaloriesBinding.inflate(layoutInflater)
        return binding.root
    }
}