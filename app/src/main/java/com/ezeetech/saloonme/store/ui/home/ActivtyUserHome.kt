/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.NavHostFragment
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.ellipsize
import com.ezeetech.saloonme.store.handler.LocationPermissionHandler
import com.ezeetech.saloonme.store.handler.PermissionHandler
import com.ezeetech.saloonme.store.util.GpsUtils
import com.ezeetech.saloonme.store.databinding.ActivityUserHomeBinding
import com.ezeetech.saloonme.store.ui.action.BottomNavigationBehavior
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.salonme.base.BaseActivity
import com.salonme.base.GPS_REQUEST
import com.salonme.base.Trace
import com.salonme.base.inflateActivity
import com.salonme.base.permission.Permission
import com.salonme.base.permission.PermissionCallback
import com.salonme.base.permission.PermissionUtils
import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.coroutines.*
import java.util.*


class ActivityUserHome : BaseActivity<ActivityUserHomeBinding>() {

    val navHostFragment: NavHostFragment by lazy {
        user_home_fragment as NavHostFragment
    }
    private val navInflater by lazy {
        navHostFragment.navController.navInflater
    }
    private val navGraph by lazy { navInflater.inflate(R.navigation.nav_user_home) }

    private var selected: Int = R.id.navigation_bookings
    /*override fun onBackPressed() {
        if (selected == R.id.navigation_home) {
            finish()
        } else {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }
    }*/

    fun navigateHome(){
        binding.bottomNavigation.selectedItemId = R.id.navigation_bookings
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding =
            inflateActivity(this, R.layout.activity_user_home) as ActivityUserHomeBinding
        init()
    }

    override fun resume() {
        super.resume()
    }

    private fun init() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            Trace.i("Selected menu: " + it.itemId)
            if (it.itemId != selected) {
                selected = it.itemId
                navHostFragment.navController.navigate(it.itemId)
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }

    fun setActionBarView(toolBar: Toolbar){
        setSupportActionBar(toolBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white)
    }

   /* fun title(title: String) {
        supportActionBar?.title = title
    }*/
    override fun onSupportNavigateUp() = navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()

    /*fun appBar(show: Boolean, back: Boolean) {
        binding.layoutAppbar.layoutParams.height =
            if (show) ViewGroup.LayoutParams.WRAP_CONTENT else 0
        supportActionBar?.setDisplayHomeAsUpEnabled(back)
        supportActionBar?.setDisplayShowHomeEnabled(back)
//        blockBack = !back
    }*/
}