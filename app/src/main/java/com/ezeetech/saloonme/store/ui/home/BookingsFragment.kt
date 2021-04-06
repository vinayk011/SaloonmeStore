/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezeetech.saloonme.store.BuildConfig
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentBookingsBinding
import com.ezeetech.saloonme.store.model.Slider
import com.salonme.base.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class BookingsFragment : BaseFragment<FragmentBookingsBinding>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as ActivityUserHome).setActionBarView(binding.toolbar)
        //(activity as ActivityUserHome).appBar(show = true, back = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(activity as ActivityUserHome).title(getString(R.string.bookings))
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateFragment(
            inflater,
            container,
            R.layout.fragment_bookings
        ) as FragmentBookingsBinding
        observeClick(root)
        return root
    }

    override fun init() {
        binding.toolbar.title = getString(R.string.bookings)
    }

    override fun onBackPressed() {
            (activity as ActivityUserHome).finish()
    }



    /**
     * Get static list of images for temporary
     */
    private fun getCarousalData(sliders: List<Slider>): List<CarouselItem> {
        val list = mutableListOf<CarouselItem>()
        for(slider in sliders){
            list.add(CarouselItem(BuildConfig.WEB_SLIDER_URL + slider.image))
        }
        return list
    }


}
