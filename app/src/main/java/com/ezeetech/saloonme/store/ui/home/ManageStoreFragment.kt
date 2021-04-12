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
import androidx.core.content.ContextCompat
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.adapter.ViewPagerManageStoreAdapter
import com.ezeetech.saloonme.store.databinding.FragmentManageStoreBinding
import com.ezeetech.saloonme.store.model.ListItem
import com.google.android.material.tabs.TabLayoutMediator
import com.salonme.base.BaseFragment
import com.salonme.base.inflateFragment
import java.util.*

class ManageStoreFragment : BaseFragment<FragmentManageStoreBinding>() {

    private val titles by lazy {
        arrayOf(getString(R.string.barbers),getString(R.string.services),getString(R.string.seats))
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as ActivityUserHome).appBar(show = false, back = false)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.viewpager.adapter = ViewPagerManageStoreAdapter(this, titles)
        TabLayoutMediator(binding.tabLayout, binding.viewpager
        ) { tab, position -> tab.text = titles[position] }.attach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                R.layout.fragment_manage_store
        ) as FragmentManageStoreBinding
        observeClick(root)
        return root
    }

    override fun init() {
        binding.toolbar.title = getString(R.string.manage_store)
    }

    override fun onBackPressed() {
        (activity as ActivityUserHome).navigateHome()
    }

    private fun setManageStoreList() {
        val itemsList = ArrayList<ListItem>()
        context?.let {
            itemsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.ic_action_seat_creation),
                            getString(R.string.seat_creation),
                            getString(R.string.seat_creation_desc)
                    )
            )
            itemsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.ic_action_barber_creation),
                            getString(R.string.barber_creation),
                            getString(R.string.barber_creation_desc)
                    )
            )
            itemsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.saloon_image),
                            getString(R.string.product_creation),
                            getString(R.string.product_creation_desc)
                    )
            )
        }
    }

}
