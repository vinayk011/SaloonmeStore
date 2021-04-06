/*
 *  Created by Vinay on 1-2-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.handler

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ezeetech.saloonme.store.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.salonme.base.permission.Permission
import com.salonme.base.permission.PermissionCallback
import com.salonme.base.permission.PermissionUtils

class LocationPermissionHandler constructor(
    private val context: Context,
    private val permissionHandler: PermissionHandler
): LifecycleObserver {

    fun isPermissionGranted(): Boolean =
        context.let {
            PermissionUtils.isGranted(it, Permission.FINE_LOCATION)
        }

    var isPresenting = false
        private set

    private var locationPermissionDialog: AlertDialog? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun bind() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unbind() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        locationPermissionDialog?.dismiss()
    }

    private val locationPermissionCallback = object : PermissionCallback {
        override fun onPermissionResult(granted: Boolean, neverAsk: Boolean) {
            if (!granted) {
                reRequestLocationPermission(neverAsk)
            } else {
                isPresenting = false
                permissionHandler.permissionGranted(Permission.FINE_LOCATION)
            }
        }
    }

    private fun reRequestLocationPermission(neverAsk: Boolean) {
        locationPermissionDialog?.dismiss()
        locationPermissionDialog = null
        context.let {
            val dialog =
                MaterialAlertDialogBuilder(context).setTitle(context.getString(R.string.permission_required))
                    .setMessage(context.getString(R.string.location_required_to_find_stores))
            if (neverAsk) {
                dialog.setMessage(
                    "${context.getString(R.string.location_required_to_find_stores)}\n${context.getString(
                        R.string.turn_on_permissions_at_setting
                    )}"
                )
            }
            dialog.setCancelable(false)
            dialog.setPositiveButton(
                if (neverAsk) context.getString(R.string.settings) else context.getString(R.string.request)
            ) { view, _ ->
                if (neverAsk) {
                    view.dismiss()
                    PermissionUtils.openApplicationSettings(context)
                    isPresenting = false
                } else {
                    requestLocationPermission()
                }
            }
            dialog.setNegativeButton(context.getString(R.string.cancel)) { view, _ ->
                view.dismiss()
                isPresenting = false
            }
            locationPermissionDialog = dialog.show()
        }
    }

    private fun requestLocationPermission() {
        isPresenting = true
        permissionHandler.request(Permission.FINE_LOCATION, locationPermissionCallback)
    }

    fun requestPermission() {
        if (!isPresenting) {
            requestLocationPermission()
        }
    }
}