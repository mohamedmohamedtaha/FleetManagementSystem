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
import com.example.fleetmanagement.data.model.Supplement
import com.example.fleetmanagement.ui.home.adapter.SupSupplementAdapter
import com.example.fleetmanagement.utils.Constants.ID
import com.example.fleetmanagement.utils.Constants.SUPPLEMENT_CATEGORY_ID
import com.example.fleetmanagement.databinding.FragmentSupplementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupSupplementsFragment : BaseFragment() {
    private lateinit var binding: FragmentSupplementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSupplementById.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    hideProgressBar()
                    if (it.data != null)
                        if (it.data.isNotEmpty()) {
                            val layoutManager = GridLayoutManager(requireActivity(), 2)
                            val adapter = SupSupplementAdapter(it.data, object :
                                OnRecycleItemClick<Supplement> {
                                override fun onClick(t: Supplement, view: View) {
                                  //  val action =
                                        //SupSupplementsFragmentDirections.actionSupSupplementsFragmentToSupplementDetailsFragment(
//                                            t
//                                        )
//                                    findNavController().navigate(action)
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
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSupplementBinding.inflate(layoutInflater)
        hideToolbar()
        arguments?.let {
            val id = it.getString(ID).toString()
            val params = HashMap<String, String>()
            params[SUPPLEMENT_CATEGORY_ID] = id
        }
        return binding.root
    }
}