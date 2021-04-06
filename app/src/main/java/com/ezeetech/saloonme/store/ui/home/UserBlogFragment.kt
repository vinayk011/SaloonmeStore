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
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezeetech.saloonme.store.ui.home.UserBlogFragmentDirections
import com.ezeetech.saloonme.store.veiw_model.UserBlogViewModel
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.adapter.AdapterUserBlog
import com.ezeetech.saloonme.store.databinding.FragmentUserBlogBinding
import com.ezeetech.saloonme.store.listener.UserBlogActionListener
import com.ezeetech.saloonme.store.model.UserBlog
import com.salonme.base.BaseFragment
import com.salonme.base.Trace
import com.salonme.base.inflateFragment
import java.util.ArrayList

class UserBlogFragment : BaseFragment<FragmentUserBlogBinding>() {

    private var blogList = ArrayList<UserBlog>()
    private lateinit var viewModel: UserBlogViewModel
    private val adapterUserBlog by lazy {
        context?.let { AdapterUserBlog(it, blogList, userBlogActionListener) }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // (activity as ActivityUserHome).appBar(show = false, back = false)
        viewModel = ViewModelProvider(this).get(UserBlogViewModel::class.java)
        initViewModel()
        viewModel.getUserBlogList()
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
            R.layout.fragment_user_blog
        ) as FragmentUserBlogBinding
        observeClick(root)
        return root
    }

    override fun init() {
        context?.let {
            binding.recyclerUserBlog.layoutManager =
                LinearLayoutManager(context)
        }
        postponeEnterTransition()
        binding.recyclerUserBlog.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    override fun onBackPressed() {
            (activity as ActivityUserHome).navigateHome()
    }
    private fun initViewModel() {
        viewModel._userBlogViewModel.observe(viewLifecycleOwner, Observer {
            blogList = it as ArrayList<UserBlog>
            binding.recyclerUserBlog.adapter = adapterUserBlog
        })
    }

    private val userBlogActionListener = object : UserBlogActionListener {
        override fun onReadMoreClicked(userBlog: UserBlog, imageView: ImageView, textView: TextView) {
            Trace.i("Read more blog:" + userBlog.title)
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElements(
                    mapOf(imageView to userBlog.blogId,
                    textView to userBlog.title)
                ).build()
            val action = UserBlogFragmentDirections.navToUserBlogDetailsFragment(userBlog)
            (activity as ActivityUserHome).navHostFragment.navController.navigate(action, extras)
        }
    }

}
