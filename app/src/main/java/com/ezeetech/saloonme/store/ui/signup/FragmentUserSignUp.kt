package com.ezeetech.saloonme.store.ui.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.InputType
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ezeetech.saloonme.LoadingView
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.home
import com.ezeetech.saloonme.store.network_call.SignUpNetworkCall
import com.ezeetech.saloonme.store.util.DateUtil
import com.ezeetech.saloonme.store.util.toDate
import com.ezeetech.saloonme.store.databinding.FragmentUserSignUpBinding
import com.ezeetech.saloonme.store.network.model.NetworkResponse
import com.ezeetech.saloonme.store.network.model.SignInResponse
import com.ezeetech.saloonme.store.network.model.SignUpResponse
import com.ezeetech.saloonme.store.network_call.SignInNetworkCall
import com.ezeetech.saloonme.store.ui.login.ActivityUserAccount
import com.ezeetech.saloonme.store.ui.signup.SignUpModel
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.salonme.base.*
import io.paperdb.Paper
import java.lang.StringBuilder


class FragmentUserSignUp : BaseFragment<FragmentUserSignUpBinding>(){
    private val model: SignUpModel by lazy {
        SignUpModel()
    }
    private val networkCall by lazy {
        ViewModelProvider(this).get(SignUpNetworkCall::class.java)
    }
    private val signInNetworkCall by lazy {
        ViewModelProvider(this).get(SignInNetworkCall::class.java)
    }
    private var networkLoader: LoadingView? = null

    private val materialDateBuilder by lazy {
        MaterialDatePicker.Builder.datePicker()
    }

    private val materialDatePicker by lazy {
        materialDateBuilder.build()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private val categories = listOf("MEN", "WOMEN", "KIDS")

    private val selectedCategories: MutableSet<String> = mutableSetOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true /* enabled by default */) {
                    override fun handleOnBackPressed() {
                        // Handle the back button event
                        (activity as ActivityUserAccount).navHostFragment.navController.navigateUp();
                    }
                }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        // The callback can be enabled or disabled here or in the lambda
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = inflateFragment(
                inflater,
                container,
                R.layout.fragment_user_sign_up
        ) as FragmentUserSignUpBinding
        observeClick(root)
        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        binding.termsServices.text = Html.fromHtml(
                getString(R.string.terms_services_text),
                HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        binding.termsServices.isClickable = true
        binding.termsServices.movementMethod = LinkMovementMethod.getInstance()
        materialDateBuilder.setTitleText("Select a Date")
        binding.model = model
        buildCategoriesUI()
        networkCall.getResult().observe(this, networkResult)
        signInNetworkCall.getResult().observe(this, signInNetworkResult)
        binding.signUpButton.setOnClickListener {
            processSignUp()
        }
    }

    private fun processSignUp() {
        if (model.isValid(context)) {
            if (!binding.termsServicesCheckbox.isChecked) {
                showSnackBar(
                        getString(R.string.error_accept_terms),
                        binding.termsServicesCheckbox
                )
                return
            }
            if (selectedCategories.size <= 0) {
                showSnackBar(
                        getString(R.string.error_select_category),
                        binding.cgCategory
                )
                return
            }
            var finalCategories = StringBuilder()
            for (category: String in selectedCategories) {
                when (category) {
                    getString(R.string.category_men) -> finalCategories.append(1).append(",")
                    getString(R.string.category_women) -> finalCategories.append(2).append(",")
                    getString(R.string.category_kids) -> finalCategories.append(3).append(",")
                }
            }
            model.category.set(finalCategories.toString())
            Trace.i("SignUp Request: ${model.getNetworkRequest()}")
            if (networkLoader?.isShowing == true) {
                networkLoader?.dismiss()
            }
            context?.let {
                networkLoader = LoadingView(it)
                networkLoader?.let { nl ->
                    addDialog(nl)
                    nl.show()
                }
                networkCall.execute(model.getNetworkRequest())
            }
        }
    }

    private fun buildCategoriesUI() {
        for (category: String in categories) {
            var chip = layoutInflater.inflate(R.layout.single_chip_layout, binding.cgCategory, false) as Chip
            chip.text = category
            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    selectedCategories.add(buttonView.text.toString())
                else
                    selectedCategories.remove(buttonView.text.toString())
            }
            binding.cgCategory.addView(chip)
        }
    }

    private val networkResult = Observer<NetworkResponse<SignUpResponse>> { res ->
        when (res) {
            is NetworkResponse.Success -> {
                if (res.response?.status == "sucess") {
                    showSnackBar(res.response?.message)
                    signInNetworkCall.execute(model.getNetworkRequestForSignIn())
                } else {
                    res.response?.message?.let { showSnackBar(it) }
                }
            }
            is NetworkResponse.Error -> {
                networkLoader?.dismiss()
                if (res.error.isNotEmpty()) {
                    showSnackBar(res.error[0].asString())
                }
            }
            is NetworkResponse.Exception -> {
                networkLoader?.dismiss()
                showSnackBar(getString(R.string.something_went_wrong))
            }
        }
        model.getConfirmPassword().set("")
    }

    private val signInNetworkResult = Observer<NetworkResponse<SignInResponse>> { res ->
        networkLoader?.dismiss()
        when (res) {
            is NetworkResponse.Success -> {
                if (res.response?.status == "sucess") {
                    context?.let { home(it, null, true) }
                } else {
                    showSnackBar(res.response?.message ?: "null")
                }
            }
            is NetworkResponse.Error -> {
                if (res.error.isNotEmpty()) {
                    showSnackBar(res.error[0].asString())
                }
            }
            is NetworkResponse.Exception -> {
                showSnackBar(getString(R.string.something_went_wrong))
            }
        }
        model.getPassword().set("")
    }
}