/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.network.model

import com.google.gson.annotations.SerializedName


data class SignInRequest(
        @SerializedName("owner_email")
        val username: String,
        val password: String
)

data class SignUpRequest(
    val first_name: String,
    val last_name: String,
    val store_name: String,
    val mobile_number: String,
    val password: String,
    val email: String,
    val categories:String
)

data class UserRequest(
    val mobile: String,
    val name: String,
    val email: String
)