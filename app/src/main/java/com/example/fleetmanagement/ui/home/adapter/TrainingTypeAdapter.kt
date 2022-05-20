package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Program
import com.example.fleetmanagement.databinding.RowTrainingTypeBinding

class TrainingTypeAdapter(
    private val items: ArrayList<Program>,
    val onClick: OnRecycleItemClick<Program>
) : RecyclerView.Adapter<TrainingTypeAdapter.TrainingTypeViewHolder>() {


    inner class TrainingTypeViewHolder(val binding: RowTrainingTypeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingTypeViewHolder {
        return TrainingTypeViewHolder(
            RowTrainingTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrainingTypeViewHolder, position: Int) {
        val item = items[position]
        holder.binding.checkBoxTrainingType.text = item.name
        holder.binding.checkBoxTrainingType.isChecked = item.isCheck
        holder.binding.checkBoxTrainingType.setOnClickListener {
            item.isCheck = !item.isCheck
            //  item.isCheck = true
            onClick.onClick(item, it)
            if (!item.isCheck)
                holder.binding.checkBoxTrainingType.isChecked = false
            else
                holder.binding.checkBoxTrainingType.isChecked = item.isCheck
        }
    }

    fun getSelected(): ArrayList<String> {
        val selected = ArrayList<String>()
        for (item in items) {
            if (item.isCheck)
                selected.add(item.name)
            else selected.remove(item.name)
        }
        return selected
    }

    override fun getItemCount() = items.size
}