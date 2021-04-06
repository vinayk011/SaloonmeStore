/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.network.model



data class NetworkError(
    val status: String,
    val message: String?
) {
    fun asString(): String {
        message?.let {
            return "$status - $message"
        }
        return status
    }
}

