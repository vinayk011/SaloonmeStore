/*
 *  Created by Vinay on 9-2-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.ui.store

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.fragment.NavHostFragment
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.ActivityUserStoreBinding
import com.salonme.base.BaseActivity
import com.salonme.base.CATEGORY
import com.salonme.base.Trace
import com.salonme.base.inflateActivity
import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.android.synthetic.main.activity_user_store.*

class ActivityUserStores : BaseActivity<ActivityUserStoreBinding>() {
    val navHostFragment: NavHostFragment by lazy {
        user_store_fragment as NavHostFragment
    }
    private val navInflater by lazy {
        navHostFragment.navController.navInflater
    }
    private val navGraph by lazy { navInflater.inflate(R.navigation.nav_user_store) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding =
            inflateActivity(this, R.layout.activity_user_store) as ActivityUserStoreBinding
        init()
    }

    override fun resume() {
        super.resume()

    }

    override fun destroy() {
        super.destroy()
        Trace.i("Destroyed.")
    }
    private fun init() {
        /*intent?.let {
            it.getStringExtra(CATEGORY)?.let { it1 ->
                title(title = it1)
            }
        }*/
        intent.extras?.let {
            for (key in it.keySet()) {
                navGraph.addArgument(
                    key,
                    NavArgument.Builder().setType(NavType.StringType).setDefaultValue(it.get(key)).build()
                )
            }
        }
        navHostFragment.navController.graph = navGraph
    }

    fun title(title: String) {
        supportActionBar?.title = title
    }

    /*fun toolBar(show: Boolean, back: Boolean) {
        binding.toolbar.visibility = if (show) View.VISIBLE else View.GONE
        supportActionBar?.setDisplayHomeAsUpEnabled(back)
        supportActionBar?.setDisplayShowHomeEnabled(back)
    }*/

    fun setActionBarView(toolBar: Toolbar){
        setSupportActionBar(toolBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white)
    }
    override fun onSupportNavigateUp(): Boolean{
        return  navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}