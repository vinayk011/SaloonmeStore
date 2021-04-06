package com.ezeetech.saloonme.store.network_call

import android.app.Application
import com.ezeetech.saloonme.store.network.model.NetworkModelWithSession
import com.ezeetech.saloonme.store.network.model.User
import com.ezeetech.saloonme.store.network.model.UserProfileResponse
import com.salonme.base.Trace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileNetworkCall (application: Application) :
        NetworkModelWithSession<UserProfileResponse, User, UserProfileResponse>(application),
        Callback<UserProfileResponse> {
    override fun execute(req: User) {
        onDestroy()
        restServices.getUserProfile(req.ownerNumber, req.token).let {
            call = it
            it.enqueue(this)
        }
    }

    override fun reExecute(): Boolean {
        getClone()?.let {
            it.enqueue(this)
            return true
        }
        return false
    }

    override fun onCleared() {
        onDestroy()
    }

    override fun onResponse(call: Call<UserProfileResponse>, response: Response<UserProfileResponse>) {
        if (response.isSuccessful) {
            //todo check sucess spelling mistake
            if(response.body() != null && response.body()!!.status == "sucess") {
                Trace.i("User profile res: " + response.body())
            }
            setValue(response.body())
        } else {
            setError(parseError(response))
        }
    }

    /*private fun result() {
        GlobalScope.launch(Dispatchers.Main) {
            setValue(true)
        }
    }*/

    override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
        if (!retry()) {
            setException(t)
        }
    }

    override fun retry(): Boolean {
        return false
    }

}