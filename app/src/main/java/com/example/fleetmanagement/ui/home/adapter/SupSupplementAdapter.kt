package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Supplement
import com.example.fleetmanagement.databinding.RowCustomSupplementBinding

class SupSupplementAdapter(private val supplements: List<Supplement>, val onClick: OnRecycleItemClick<Supplement>) :
    RecyclerView.Adapter<SupSupplementAdapter.ViewHolderSupSupplements>() {

    inner class ViewHolderSupSupplements(val binding: RowCustomSupplementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSupSupplements {
        return ViewHolderSupSupplements(
            RowCustomSupplementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderSupSupplements, position: Int) {
        val supplement = supplements[position]
        imageTransformSupplements(holder.binding.imageViewPicture, supplement.supplementImage)
        holder.binding.textViewTitle.text = supplement.nameEn
        holder.itemView.setOnClickListener {
            onClick.onClick(supplement,it)
        }
    }

    override fun getItemCount() = supplements.size
}