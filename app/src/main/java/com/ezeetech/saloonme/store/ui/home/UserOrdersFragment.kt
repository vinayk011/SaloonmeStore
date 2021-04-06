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
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentUserOrdersBinding
import com.salonme.base.BaseFragment
import com.salonme.base.inflateFragment

class UserOrdersFragment : BaseFragment<FragmentUserOrdersBinding>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as ActivityUserHome).appBar(show = false, back = false)
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
            R.layout.fragment_user_orders
        ) as FragmentUserOrdersBinding
        observeClick(root)
        return root
    }

    override fun init() {

    }

    override fun onBackPressed() {
            (activity as ActivityUserHome).navigateHome()
    }

}
