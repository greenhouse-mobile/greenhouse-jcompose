package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.shared.data.remote.UserService
import com.integradis.greenhouse.shared.data.remote.UserServiceFactory
import com.integradis.greenhouse.shared.domain.UserData
import com.integradis.greenhouse.shared.domain.UserWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class UserRepository(private val userService: UserService = UserServiceFactory.getUserService()) {
    fun getUser(username: String, callback: (List<UserData>) -> Unit) {
            val getUser = userService.getUser(username)
            getUser.enqueue(object: Callback<UserWrapper> {
                override fun onResponse(
                    call: Call<UserWrapper>,
                    response: Response<UserWrapper>
                ) {
                    if(response.isSuccessful){
                        callback(response.body()?.users ?: emptyList())
                    }
                }
                override fun onFailure(
                    call: Call<UserWrapper>,
                    t: Throwable
                ) {
                    t.message?.let {
                        Log.d("UserRepository", it)
                    }
                }
            })
        }

    }