/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.ui.home

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentUserProfileBinding
import com.ezeetech.saloonme.store.model.UserBlog
import com.ezeetech.saloonme.store.network.model.User
import com.ezeetech.saloonme.store.network.model.UserProfile
import com.ezeetech.saloonme.store.veiw_model.UserBlogViewModel
import com.ezeetech.saloonme.store.veiw_model.UserProfileViewModel
import com.google.android.material.appbar.AppBarLayout
import com.salonme.base.*
import io.paperdb.Paper
import java.util.ArrayList


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {
    private lateinit var profileViewModel: UserProfileViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as ActivityUserHome).appBar(show = false, back = false)
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
    }

    private fun updateUserData() {
        var ownerProfile = Paper.book().read<User>(DB_STORE_OWNER)
        Trace.i("Owner profile email: " + ownerProfile.ownerEmail + ", number: " + ownerProfile.ownerNumber)
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
}
