package com.ezeetech.saloonme.store.ui.signup

import android.content.Context
import androidx.databinding.ObservableField
import com.ezeetech.saloonme.store.*
import com.ezeetech.saloonme.store.network.model.SignInRequest
import com.ezeetech.saloonme.store.network.model.SignUpRequest

/**
 * Created by Vinay.
 * Copyright (c) 2021 EzeeTech. All rights reserved.
 */

class SignUpModel {
    private val firstName = ObservableField<String>()
    private val lastName = ObservableField<String>()
    private val storeName = ObservableField<String>()
    private val mobileNumber = ObservableField<String>()
    private val password = ObservableField<String>()
    private val confirmPassword = ObservableField<String>()
    private val email = ObservableField<String>()
    val category = ObservableField<String>()

    val firstNameError = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val storeNameError = ObservableField<String>()
    val mobileNumberError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val confirmPasswordError = ObservableField<String>()
    val emailError = ObservableField<String>()

    fun getFirstName(): ObservableField<String> {
        firstNameError.set(null)
        return firstName
    }

    fun getLastName(): ObservableField<String>{
        lastNameError.set(null)
        return lastName
    }
    fun getStoreName(): ObservableField<String>{
        storeNameError.set(null)
        return storeName
    }

    fun getMobileNumber(): ObservableField<String> {
        mobileNumberError.set(null)
        return mobileNumber
    }

    fun getPassword(): ObservableField<String> {
        passwordError.set(null)
        return password
    }

    fun getConfirmPassword(): ObservableField<String> {
        confirmPasswordError.set(null)
        return confirmPassword
    }

    fun getEmail(): ObservableField<String> {
        emailError.set(null)
        return email
    }


    private fun firstName(): String {
        firstName.get()?.let {
            return it
        }
        return ""
    }

    private fun lastName(): String {
        lastName.get()?.let {
            return it
        }
        return ""
    }

    private fun storeName(): String{
        storeName.get()?.let {
            return it
        }
        return ""
    }

    private fun mobileNumber(): String {
        mobileNumber.get()?.let {
            return it
        }
        return ""
    }

    private fun nationalMobileNumber(): String {
        return mobileNumber().getNationalMobileNumber()
    }
    private fun password(): String {
        password.get()?.let {
            return it
        }
        return ""
    }

    private fun confirmPassword(): String {
        confirmPassword.get()?.let {
            return it
        }
        return ""
    }

    private fun email(): String {
        email.get()?.let {
            return it
        }
        return ""
    }
    private fun categories(): String {
        category.get()?.let {
            return it
        }
        return ""
    }

    fun isValid(context: Context?): Boolean {
        firstNameError.set(null)
        lastNameError.set(null)
        storeNameError.set(null)
        mobileNumberError.set(null)
        passwordError.set(null)
        confirmPasswordError.set(null)
        emailError.set(null)
        if (firstName().isNotBlank() && lastName().isNotBlank() && storeName().isNotBlank() && mobileNumber().isNotBlank() && mobileNumber().isMobileNumber() && password().length > 4 &&
            password() == confirmPassword() && email().isEmailAddress()
        ) {
            return true
        } else {
            if (firstName().isBlank()) {
                firstNameError.set(context?.getString(R.string.empty_first_name))
            }
            if (lastName().isBlank()) {
                lastNameError.set(context?.getString(R.string.empty_last_name))
            }
            if (storeName().isBlank()) {
                storeNameError.set(context?.getString(R.string.empty_store_name))
            }
            if (mobileNumber().isBlank()) {
                mobileNumberError.set(context?.getString(R.string.empty_mobile_number))
            } else if (!mobileNumber().isMobileNumber()) {
                mobileNumberError.set(context?.getString(R.string.valid_mobile_number))
            }
            if (password().length <= 4) {
                passwordError.set(context?.getString(R.string.valid_password))
            }
            if (confirmPassword() != password()) {
                confirmPasswordError.set(context?.getString(R.string.not_same_password))
            }
            if (email().isEmpty()) {
                emailError.set(context?.getString(R.string.enter_email))
            } else if (!email().isEmailAddress()) {
                emailError.set(context?.getString(R.string.valid_email))
            }
        }
        return false
    }

    fun getNetworkRequestForSignIn(): SignInRequest =
        SignInRequest(email(), password())//encrypt(password())/*, getAppId()*/)

    fun getNetworkRequest(): SignUpRequest =
        SignUpRequest(firstName(), lastName(), storeName(), nationalMobileNumber(), password(), email(), categories())
}