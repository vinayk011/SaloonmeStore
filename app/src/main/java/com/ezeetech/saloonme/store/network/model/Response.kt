/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.network.model

import com.ezeetech.saloonme.store.model.Slider
import com.google.gson.annotations.SerializedName


data class SignInResponse(
        val status: String,
        val accessToken: String,
        @SerializedName("data")
        val userData: List<User>,
        @SerializedName("message")
        val message: String
)

data class SignUpResponse(
        val status: String,
        val message: String
)

data class UserProfileResponse(
        val status: String,
        val message:String,
        @SerializedName("data")
        val userProfile:List<UserProfile>
)
data class User(
        @SerializedName("owner_email")
        val ownerEmail: String,
        @SerializedName("owner_number")
        val ownerNumber: String,
        @SerializedName("token")
        val token: String
)

data class UserProfile(
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("store_name")
        val storeName: String,
        @SerializedName("category")
        val category: String,
        @SerializedName("profile_image")
        val profileImage: String,
        @SerializedName("profile_cover_image")
        val profileCoverImage: String,
        @SerializedName("phone_number")
        val mobile: String,
        @SerializedName("website_url")
        val websiteUrl: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("lat")
        val latitude: String,
        @SerializedName("lng")
        val longitude: String,
        @SerializedName("shop_established")
        val shopEstablished: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("owner_email")
        val ownerEmail: String,
        @SerializedName("owner_number")
        val ownerNumber: String,
        @SerializedName("owner_gender")
        val ownerGender: String,
        @SerializedName("google_pay")
        val googlePay: String,
        @SerializedName("pancardno")
        val pancardNumber: String,
        @SerializedName("bank_accountno")
        val bankAcNumber: String,
        @SerializedName("account_type")
        val accountType: String,
        @SerializedName("ifsc_code")
        val ifscCode: String,
        @SerializedName("account_holder")
        val accountHolder: String,
        @SerializedName("bank_name")
        val bankName: String,
        @SerializedName("aadhar_card")
        val aadharCard: String,
        @SerializedName("aadhar_cardimg")
        val aadharCardImg: String,
        @SerializedName("long_bio")
        val longBio: String,
        @SerializedName("short_bio")
        val shortBio: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("token")
        val token: String
)

data class SlidersResponse(
        val success: Boolean,
        val message: String,
        val sliders: List<Slider>
)