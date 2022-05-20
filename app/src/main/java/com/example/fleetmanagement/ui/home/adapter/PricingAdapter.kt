package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.ProgramDuration
import com.example.fleetmanagement.databinding.RowCustomRadioButtonPricingBinding

class PricingAdapter(private val onclick: OnRecycleItemClick<ProgramDuration>,private val language:String) :
    ListAdapter<ProgramDuration, PricingAdapter.ViewHolderPricing>(PricingDiffCallback()) {


    inner class ViewHolderPricing(val binding: RowCustomRadioButtonPricingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPricing {
        return ViewHolderPricing(
            RowCustomRadioButtonPricingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPricing, position: Int) {
        val item = getItem(position)
        holder.binding.programDuration = item
        holder.binding.language = language
        holder.binding.radioButtonPricing.setOnClickListener {
            onclick.onClick(item,it)
        }
    }
}


class PricingDiffCallback : DiffUtil.ItemCallback<ProgramDuration>() {
    override fun areItemsTheSame(oldItem: ProgramDuration, newItem: ProgramDuration): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProgramDuration, newItem: ProgramDuration): Boolean {
        return oldItem == newItem
    }
}