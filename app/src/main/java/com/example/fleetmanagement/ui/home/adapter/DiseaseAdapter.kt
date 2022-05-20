package com.example.fleetmanagement.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Disease
import com.example.fleetmanagement.databinding.RowCustomDiseaseBinding

class DiseaseAdapter(
    private val items: ArrayList<Disease>,
    val onClick: OnRecycleItemClick<Disease>,
    private val language: String
) : RecyclerView.Adapter<DiseaseAdapter.ViewHolderDisease>() {
    inner class ViewHolderDisease(val binding: RowCustomDiseaseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDisease {
        return ViewHolderDisease(
            RowCustomDiseaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderDisease, position: Int) {
        val item = items[position]
        holder.binding.disease = item
        Log.d("LoginFragment","$language")
        holder.binding.language = language
        holder.itemView.setOnClickListener { onClick.onClick(item, it) }
    }

    override fun getItemCount() = items.size
}