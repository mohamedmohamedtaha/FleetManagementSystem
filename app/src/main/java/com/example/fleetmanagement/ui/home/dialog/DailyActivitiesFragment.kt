package com.example.fleetmanagement.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.DailyActivities
import com.example.fleetmanagement.ui.home.adapter.DailyActivitiesAdapter
import com.example.fleetmanagement.databinding.FragmentDailyActivitiesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DailyActivitiesFragment(
    val position: (DailyActivities) -> Unit,
    private val items: ArrayList<DailyActivities>,
    private val language: String
) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDailyActivitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDailyActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DailyActivitiesAdapter(items, object : OnRecycleItemClick<DailyActivities> {
            override fun onClick(t: DailyActivities, view: View) {
                position(t)
                dismiss()
            }
        }, language)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerViewDailyActivities.layoutManager = layoutManager
        binding.recyclerViewDailyActivities.adapter = adapter
    }
}