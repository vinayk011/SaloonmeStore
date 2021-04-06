package com.ezeetech.saloonme.store.veiw_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.network.model.NetworkResponse
import com.ezeetech.saloonme.store.network.model.User
import com.ezeetech.saloonme.store.network.model.UserProfile
import com.ezeetech.saloonme.store.network_call.UserProfileNetworkCall
import com.salonme.base.DB_STORE_OWNER
import com.salonme.base.Trace
import io.paperdb.Paper

class UserProfileViewModel(app:Application): AndroidViewModel(app) {

    private val userProfileNetworkCall by lazy {
         UserProfileNetworkCall(app)
    }
    private val profileViewModel  = MutableLiveData<UserProfile>()
    val _userProfileViewModel : LiveData<UserProfile>
        get() = profileViewModel

    init {
        // init api network call
        userProfileNetworkCall.getResult().observeForever { res ->
            when (res) {
                is NetworkResponse.Success -> {
                    res.response?.let {
                        Trace.i("User profile received:"+it.userProfile.toString())
                        profileViewModel.value = it.userProfile[0]
                    }
                }
                is NetworkResponse.Error -> {
                    if (res.error.isNotEmpty()) {
                        Trace.e("error while getting profile data:"+ res.error.toString())
                    }
                }
                is NetworkResponse.Exception -> {
                    Trace.e(app.getString(R.string.something_went_wrong))
                }
            }
        }
    }
    fun getUserProfile(){
        val user = Paper.book().read<User>(DB_STORE_OWNER)
        user?.let {
            Trace.i("Owner profile email: " + it.ownerEmail + ", number: " + it.ownerNumber)
            userProfileNetworkCall.execute(it)
        }

    }
}