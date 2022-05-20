package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.SupplementCategories
import com.example.fleetmanagement.ui.home.adapter.SupplementsAdapter
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentSupplementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplementFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSupplementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSupplementCategories.observe(this, {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    if (it.data != null)
                        if (it.data.isNotEmpty()) {
                            val layoutManager = GridLayoutManager(requireActivity(), 2)
                            val adapter = SupplementsAdapter(it.data,
                                object : OnRecycleItemClick<SupplementCategories> {
                                    override fun onClick(t: SupplementCategories, view: View) {
//                                        val action =
//                                            SupplementFragmentDirections.actionSupplementFragmentToSupSupplementsFragment(
//                                                t.id
//                                            )
//                                        findNavController().navigate(action)
                                    }
                                })
                            binding.recyclerViewSupplement.layoutManager = layoutManager
                            binding.recyclerViewSupplement.adapter = adapter
                        }
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSupplementBinding.inflate(layoutInflater)
        binding.recyclerViewSupplement.setOnClickListener(this)
        viewModel.getSupplemntCategories()
        hideToolbar()
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.recyclerViewSupplement -> {

            }
        }
    }

}