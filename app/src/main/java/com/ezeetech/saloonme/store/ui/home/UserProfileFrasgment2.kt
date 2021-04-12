package com.ezeetech.saloonme.store.ui.home

/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */


import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentUserProfile2Binding
import com.ezeetech.saloonme.store.databinding.FragmentUserProfileBinding
import com.ezeetech.saloonme.store.network.model.User
import com.google.android.material.appbar.AppBarLayout
import com.salonme.base.*
import io.paperdb.Paper


class UserProfileFragment2 : BaseFragment<FragmentUserProfile2Binding>(){
    private var EXPAND_AVATAR_SIZE: Float = 0F
    private var COLLAPSE_IMAGE_SIZE: Float = 0F
    private var horizontalToolbarAvatarMargin: Float = 0F
    private var cashCollapseState: Pair<Int, Int>? = null
    private var avatarAnimateStartPointY: Float = 0F
    private var avatarCollapseAnimationChangeWeight: Float = 0F
    private var isCalculated = false
    private var verticalToolbarAvatarMargin =0F

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
                R.layout.fragment_user_profile2
        ) as FragmentUserProfile2Binding
        observeClick(root)
        return root
    }

    override fun init() {
        //binding.toolbar.title = getString(R.string.profile)
        EXPAND_AVATAR_SIZE = resources.getDimension(R.dimen.default_expanded_image_size)
        COLLAPSE_IMAGE_SIZE = resources.getDimension(R.dimen.default_collapsed_image_size)
        horizontalToolbarAvatarMargin = resources.getDimension(R.dimen.activity_margin)

        binding.appBarLayout.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
                    if (isCalculated.not()) {
                        avatarAnimateStartPointY = Math.abs((appBarLayout.height - (EXPAND_AVATAR_SIZE + horizontalToolbarAvatarMargin)) / appBarLayout.totalScrollRange)
                        avatarCollapseAnimationChangeWeight = 1 / (1 - avatarAnimateStartPointY)
                        verticalToolbarAvatarMargin = (binding.animToolbar.height - COLLAPSE_IMAGE_SIZE) * 2
                        isCalculated = true
                    }
                    /**/
                    updateViews(Math.abs(i / appBarLayout.totalScrollRange.toFloat()))
                })
        updateUserData()
    }

    private fun updateUserData() {
        var ownerProfile = Paper.book().read<User>(DB_STORE_OWNER)
        Trace.i("Owner profile email: "+ownerProfile.ownerEmail+", number: "+ownerProfile.ownerNumber)
    }

    override fun onBackPressed() {
        (activity as ActivityUserHome).navigateHome()
    }

    private fun updateViews(offset: Float) {
        /* apply levels changes*/
        when (offset) {
            in 0.15F..1F -> {
                binding.tvProfileName.apply {
                    if (visibility != View.VISIBLE) visibility = View.VISIBLE
                    alpha = (1 - offset) * 0.35F
                }
            }

            in 0F..0.15F -> {
                binding.tvProfileName.alpha = (1f)
                binding.imgAvatar.alpha = 1f
            }
        }

        /** collapse - expand switch*/
        when {
            offset < SWITCH_BOUND -> Pair(TO_EXPANDED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
            else -> Pair(TO_COLLAPSED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
        }.apply {
            when {
                cashCollapseState != null && cashCollapseState != this -> {
                    when (first) {
                        TO_EXPANDED ->  {
                            /* set avatar on start position (center of parent frame layout)*/
                            binding.imgAvatar.translationX = 0F
                            /**/
                            context?.let { ContextCompat.getColor(it, R.color.color_transparent) }?.let { binding.flBackground.setBackgroundColor(it) }
                            /* hide top titles on toolbar*/
                            binding.tvProfileNameSingle.visibility = View.INVISIBLE
                        }
                        TO_COLLAPSED -> binding.flBackground.apply {
                            alpha = 0F
                            setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            animate().setDuration(250).alpha(1.0F)
                            /* show titles on toolbar with animation*/
                            binding.tvProfileNameSingle.apply {
                                visibility = View.VISIBLE
                                alpha = 0F
                                animate().setDuration(500).alpha(1.0f)
                            }
                        }
                    }
                    cashCollapseState = Pair(first, SWITCHED)
                }
                else -> {
                    cashCollapseState = Pair(first, WAIT_FOR_SWITCH)
                }
            }

            /* Collapse avatar img*/
            binding.imgAvatar.apply {
                when {
                    offset > avatarAnimateStartPointY -> {
                        val avatarCollapseAnimateOffset = (offset - avatarAnimateStartPointY) * avatarCollapseAnimationChangeWeight
                        val avatarSize = EXPAND_AVATAR_SIZE - (EXPAND_AVATAR_SIZE - COLLAPSE_IMAGE_SIZE) * avatarCollapseAnimateOffset
                        this.layoutParams.also {
                            it.height = Math.round(avatarSize)
                            it.width = Math.round(avatarSize)
                        }

                        binding.tvWorkaround.setTextSize(TypedValue.COMPLEX_UNIT_PX, offset)

                        this.translationX = ((binding.appBarLayout.width - horizontalToolbarAvatarMargin - avatarSize) / 2) * avatarCollapseAnimateOffset
                        this.translationY = ((binding.animToolbar.height  - verticalToolbarAvatarMargin - avatarSize ) / 2) * avatarCollapseAnimateOffset
                    }
                    else -> this.layoutParams.also {
                        if (it.height != EXPAND_AVATAR_SIZE.toInt()) {
                            it.height = EXPAND_AVATAR_SIZE.toInt()
                            it.width = EXPAND_AVATAR_SIZE.toInt()
                            this.layoutParams = it
                        }
                        translationX = 0f
                    }
                }
            }
        }
    }

    companion object {
        const val SWITCH_BOUND = 0.8f
        const val TO_EXPANDED = 0
        const val TO_COLLAPSED = 1
        const val WAIT_FOR_SWITCH = 0
        const val SWITCHED = 1
    }
}
