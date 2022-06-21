package com.example.fleetmanagement.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Language
import com.example.fleetmanagement.ui.home.adapter.LanguageAdapter
import com.example.fleetmanagement.databinding.FragmentLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguageFragment(val position: (Language) -> Unit, private val items: ArrayList<Language>) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LanguageAdapter(items, object : OnRecycleItemClick<Language> {
            override fun onClick(t: Language, view: View) {
                position(t)
                dismiss()
            }
        })
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerViewLanguage.layoutManager = layoutManager
        binding.recyclerViewLanguage.adapter = adapter
    }
}