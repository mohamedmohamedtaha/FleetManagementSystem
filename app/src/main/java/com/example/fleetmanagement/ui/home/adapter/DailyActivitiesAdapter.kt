package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.DailyActivities
import com.example.fleetmanagement.databinding.RowCustomDailyActivitiesBinding

class DailyActivitiesAdapter(
    private val items: ArrayList<DailyActivities>,
    val onClick: OnRecycleItemClick<DailyActivities>,
    private val language: String
) :
    RecyclerView.Adapter<DailyActivitiesAdapter.ViewHolderDailyActivities>() {
    inner class ViewHolderDailyActivities(val binding: RowCustomDailyActivitiesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyActivitiesAdapter.ViewHolderDailyActivities {
        return ViewHolderDailyActivities(
            RowCustomDailyActivitiesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: DailyActivitiesAdapter.ViewHolderDailyActivities,
        position: Int
    ) {
        val item = items[position]
        holder.binding.dailyActivities = item
        holder.binding.language = language
        holder.itemView.setOnClickListener { onClick.onClick(item, it) }
    }

    override fun getItemCount() = items.size
}