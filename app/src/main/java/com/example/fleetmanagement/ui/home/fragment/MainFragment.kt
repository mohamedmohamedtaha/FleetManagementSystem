package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.fleetmanagement.R
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.Image
import com.example.fleetmanagement.data.model.Message
import com.example.fleetmanagement.databinding.FragmentMainBinding
import com.example.fleetmanagement.ui.home.SelectionToolbar
import com.example.fleetmanagement.ui.home.activity.MainActivity
import com.example.fleetmanagement.ui.home.adapter.LoopingBannerAdapter
import com.example.fleetmanagement.ui.home.adapter.TasksListAdapter
import com.example.fleetmanagement.utils.ZoomOutPageTransformer
import com.example.fleetmanagement.utils.onError
import com.example.fleetmanagement.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(){
    private lateinit var binding: FragmentMainBinding
    private var bannerList = arrayListOf<Image>()
    private val viewPagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        var currentIndex = -1
        val runnable = Runnable {
            binding.viewPager2Banner.setCurrentItem(
                binding.viewPager2Banner.currentItem + 1, true
            )
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentIndex = position
            //Must remove before position
            binding.viewPager2Banner.removeCallbacks(runnable)
            //Auto scroll every 2.5 second
            binding.viewPager2Banner.postDelayed(runnable, 5000)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (state == ViewPager2.SCROLL_STATE_IDLE) {
                if (currentIndex == 0) {
                    binding.viewPager2Banner.setCurrentItem(bannerList.size - 2, false)
                    // binding.viewPager2Banner.setCurrentItem(bannerList.size - 1, false)
                }
                //else if(currentIndex == bannerList.size ){
                else if (currentIndex == bannerList.size - 1) {
                    binding.viewPager2Banner.setCurrentItem(1, false)
                    // binding.viewPager2Banner.setCurrentItem(0,false)
                }
            }
        }
        //index from 0 to banner.size -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTasksList.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
                is NetworkResult.Success -> {
                    hideProgressBar()
                    val adapter = TasksListAdapter(
                        it.data.message as ArrayList<Message>,
                        object : OnRecycleItemClick<Message> {
                            override fun onClick(t: Message, view: View) {
                                val action  = MainFragmentDirections.actionMainFragmentToTaskDetailsFragment(t)
                               findNavController().navigate(action)
                            }

                        })
                    binding.recyclerViewTasks.adapter = adapter
                }
                is NetworkResult.Error -> {
                    hideProgressBar()
                    binding.root.showSnackBar(it.message)
                }
                is NetworkResult.ErrorEX -> {
                    hideProgressBar()
                    binding.root.onError(it.exception)
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        bannerList = arrayListOf()
        val banner = arrayListOf<Image>()
        showToolbar()
        banner.add(Image("",R.drawable.image5))
        banner.add(Image("", R.drawable.image1))
        banner.add(Image("", R.drawable.image2))
        banner.add(Image("", R.drawable.image3))
        banner.add(Image("", R.drawable.image4))
        banner.add(Image("", R.drawable.image5))
        banner.add(Image("",R.drawable.image1))

        bannerList.addAll(banner)

        binding.viewPager2Banner.adapter = LoopingBannerAdapter(bannerList)
        binding.viewPager2Banner.clipChildren = false
        binding.viewPager2Banner.clipToPadding = false
        binding.viewPager2Banner.offscreenPageLimit = 3
        binding.dotsIndicator.setViewPager2(binding.viewPager2Banner)
        binding.viewPager2Banner.registerOnPageChangeCallback(viewPagerChangeCallback)
        val zoomOutPageTransformer = ZoomOutPageTransformer()
        binding.viewPager2Banner.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }
        (activity as MainActivity).setSelectionToolbar(object : SelectionToolbar {
            override fun onSelectProfileImage(v: View) {
                findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (dataStoreViewModel.getAccess() != null) {
            binding.materialCardViewNoData.visibility = View.GONE
            binding.recyclerViewTasks.visibility = View.VISIBLE
            binding.textViewYourTrip.visibility = View.VISIBLE

            viewModel.getTasksList(
                dataStoreViewModel.getAccess().toString(),
                dataStoreViewModel.getPassword().toString(),
                "list"
            )
        } else {
            binding.materialCardViewNoData.visibility = View.VISIBLE
            binding.recyclerViewTasks.visibility = View.GONE
            binding.textViewYourTrip.visibility = View.GONE

        }
    }
}