package com.example.fleetmanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fleetmanagement.data.model.Image
import com.example.fleetmanagement.databinding.RowImageBannerBinding

class LoopingBannerAdapter(private val banner:ArrayList<Image>) : RecyclerView.Adapter<LoopingBannerAdapter.ViewHolderBanner>() {
    inner class ViewHolderBanner(val binding:RowImageBannerBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBanner {
        return ViewHolderBanner(RowImageBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderBanner, position: Int) {
        val banner = banner[position]
        imageTransformProfile(holder.binding.imageViewBanner, banner.image)

    }

    override fun getItemCount() = banner.size
}




















