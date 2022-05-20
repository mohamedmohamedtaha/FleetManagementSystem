package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.model.Supplement
import com.example.fleetmanagement.ui.home.adapter.imageTransformSupplements
import com.example.fleetmanagement.utils.Constants.SUPPLEMENT
import com.example.fleetmanagement.databinding.FragmentSupplementDetailsBinding


class SupplementDetailsFragment : BaseFragment() {
    private var supplement: Supplement? = null
    private lateinit var binding: FragmentSupplementDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSupplementDetailsBinding.inflate(layoutInflater)
        hideToolbar()
        arguments?.let {
            supplement = it.getParcelable(SUPPLEMENT)
            imageTransformSupplements(
                binding.imageViewSupplement,
                supplement!!.supplementImage
            )
            binding.data = supplement
            binding.textViewTitle.text = supplement!!.nameEn
            binding.textViewDescription.text = supplement!!.descriptionEn
            if (supplement!!.supplementFactSet.isNotEmpty())
                binding.textViewSupplementFactSet.text =
                    supplement!!.supplementFactSet[0].amount
        }
        return binding.root
    }
}