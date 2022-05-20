package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.SupplementCategories
import com.example.fleetmanagement.databinding.RowCustomSupplementBinding

class SupplementsAdapter(private val supplements: List<SupplementCategories>,val onClick:OnRecycleItemClick<SupplementCategories>) :
    RecyclerView.Adapter<SupplementsAdapter.ViewHolderSupplements>() {

    inner class ViewHolderSupplements(val binding: RowCustomSupplementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSupplements {
        return ViewHolderSupplements(
            RowCustomSupplementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderSupplements, position: Int) {
        val supplement = supplements[position]
        imageTransformSupplements(holder.binding.imageViewPicture, supplement.image)
        holder.binding.textViewTitle.text = supplement.nameEn
        holder.itemView.setOnClickListener {
            onClick.onClick(supplement,it)
        }
    }

    override fun getItemCount() = supplements.size
}