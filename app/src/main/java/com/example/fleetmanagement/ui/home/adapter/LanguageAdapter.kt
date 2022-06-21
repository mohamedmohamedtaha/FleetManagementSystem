package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.model.Language
import com.example.fleetmanagement.databinding.RowCustomLanguageBinding

class LanguageAdapter(
    private val items: ArrayList<Language>,
    val onClick: OnRecycleItemClick<Language>
) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolderLanguage>() {
    inner class ViewHolderLanguage(val binding: RowCustomLanguageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageAdapter.ViewHolderLanguage {
        return ViewHolderLanguage(
            RowCustomLanguageBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LanguageAdapter.ViewHolderLanguage, position: Int) {
        val item = items[position]
        holder.binding.language = item
        holder.itemView.setOnClickListener { onClick.onClick(item, it) }
    }

    override fun getItemCount() = items.size
}