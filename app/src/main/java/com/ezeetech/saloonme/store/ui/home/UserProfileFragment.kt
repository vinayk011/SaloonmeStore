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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.adapter.AdapterProfileItem
import com.ezeetech.saloonme.store.databinding.FragmentUserProfileBinding
import com.ezeetech.saloonme.store.listener.ItemClickListener
import com.ezeetech.saloonme.store.model.ListItem
import com.ezeetech.saloonme.store.network.model.UserProfile
import com.ezeetech.saloonme.store.veiw_model.UserProfileViewModel
import com.salonme.base.*
import io.paperdb.Paper
import java.util.*


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {
    private lateinit var profileViewModel: UserProfileViewModel
    private var profileDetailsList = ArrayList<ListItem>()
    private val adapterProfileItems by lazy {
        context?.let { AdapterProfileItem(it, profileDetailsList, profileDetailsClickListener) }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        initViewModel()
        profileViewModel.getUserProfile()
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
                R.layout.fragment_user_profile
        ) as FragmentUserProfileBinding
        observeClick(root)
        return root
    }

    override fun init() {
        binding.toolbar.title = getString(R.string.profile)
        binding.profile = Paper.book().read(DB_STORE_OWNER_PROFILE)
        Trace.i("Profile login data:"+Paper.book().read(DB_STORE_OWNER))
        setProfileDetailsList()
        context?.let {
            binding.recyclerProfileDetails.layoutManager =
                    LinearLayoutManager(context)
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
            binding.recyclerProfileDetails.addItemDecoration(itemDecorator)
            binding.recyclerProfileDetails.adapter = adapterProfileItems
        }
    }

    private fun setProfileDetailsList() {
        val detailsList = ArrayList<ListItem>()
        context?.let {
            detailsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.ic_action_account),
                            getString(R.string.bank_details),
                            getString(R.string.bank_details_caption)
                    )
            )
            detailsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.ic_action_store),
                            getString(R.string.store_details),
                            getString(R.string.store_details_caption)
                    )
            )
            detailsList.add(
                    ListItem(
                            ContextCompat.getDrawable(it, R.drawable.ic_action_profile),
                            getString(R.string.profile_details),
                            getString(R.string.profile_details_caption)
                    )
            )
        }
        profileDetailsList = detailsList
    }

    private fun initViewModel() {
        profileViewModel._userProfileViewModel.observe(viewLifecycleOwner, Observer {
            Paper.book().write(DB_STORE_OWNER_PROFILE, it)
            binding.profile = it as UserProfile
        })
    }

    override fun onBackPressed() {
        (activity as ActivityUserHome).navigateHome()
    }

    private val profileDetailsClickListener = object : ItemClickListener<ListItem> {
        override fun onClicked(item: ListItem) {
            Trace.i("selected profile: "+item.title)
        }
    }
}
