/*
 * *
 *  * Created by Vinay.
 *  * Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.network

import com.ezeetech.saloonme.store.network.model.*
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RestService {
    @FormUrlEncoded
    @POST("ShopLogin")
    fun signIn(@Field("owner_email") email: String, @Field("password") password: String): Call<SignInResponse>

    @GET("SalonProfile/{mobile}/{token}")
    fun getUserProfile(@Path("mobile") mobile: String, @Path("token") token:String): Call<UserProfileResponse>

    @POST("registry/users/login/renew")
    fun renew(@Body signInRequest: SignInRequest): Call<SignInResponse>

    @FormUrlEncoded
    @POST("ShopRegister")
    fun signUp(@Field("first_name") firstName: String, @Field("last_name") lastName: String,
               @Field("store_name") storeName: String, @Field("owner_email") ownerEmail: String,
               @Field("owner_number") ownerNumber: String, @Field("category") category: String,
               @Field("password") password: String): Call<SignUpResponse>

    @GET("api_get_sliders")
    fun getSliders(): Call<SlidersResponse>

    /*@POST("registry/users/login/renew")
    fun renew(@Body signInRequest: SignInRequest): Call<SignInResponse>

    @POST("registry/pin/new")
    fun forgotPin(@Body jsonObject: JsonObject): Call<Void>

    @POST("users/pin")
    fun validateNewPin(@Body jsonObject: JsonObject): Call<Void>

    @POST("registry/otp/new")
    fun requestOTP(@Body jsonObject: JsonObject): Call<Void>

    @PUT("registry/otp")
    fun changeMobile(@Body jsonObject: JsonObject): Call<Void>

    @POST("users/otp")
    fun validateCode(@Body jsonObject: JsonObject): Call<Void>

    @PUT("users")
    fun saveUser(@Body userRequest: UserRequest): Call<Void>

    @GET("users")
    fun getUser(): Call<UserResponse>

    @GET("users/images")
    fun getImage(): Call<ResponseBody>

    @Multipart
    @PUT("users/images")
    fun saveImage(@Part file: MultipartBody.Part): Call<Void>

    @DELETE("users/images")
    fun deleteImage(): Call<Void>

    @PUT("users/pin")
    fun changePIN(@Body jsonObject: JsonObject): Call<Void>*/

}