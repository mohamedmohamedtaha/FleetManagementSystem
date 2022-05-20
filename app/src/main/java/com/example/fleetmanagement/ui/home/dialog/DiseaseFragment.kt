package com.example.fleetmanagement.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Disease
import com.example.fleetmanagement.ui.home.adapter.DiseaseAdapter
import com.example.fleetmanagement.databinding.FragmentDiseaseBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DiseaseFragment(
    val position: (Disease) -> Unit,
    private val items: ArrayList<Disease>,
    private val language: String
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDiseaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDiseaseBinding.inflate(inflater, container, false)
        val adapter = DiseaseAdapter(items, object : OnRecycleItemClick<Disease> {
            override fun onClick(t: Disease, view: View) {
                position(t)
                dismiss()
            }
        }, language)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerViewDisease.layoutManager = layoutManager
        binding.recyclerViewDisease.adapter = adapter
        return binding.root
    }
}





