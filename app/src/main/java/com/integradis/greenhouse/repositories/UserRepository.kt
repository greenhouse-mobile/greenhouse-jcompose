package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.shared.data.remote.UserService
import com.integradis.greenhouse.shared.data.remote.UserServiceFactory
import com.integradis.greenhouse.shared.domain.UserData
import com.integradis.greenhouse.shared.domain.UserWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class UserRepository(
    private val userService: UserService = UserServiceFactory.getUserService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    private val token: String?
        get() = sharedPreferencesHelper.getToken()

    fun getUser(username: String, callback: (List<UserData>) -> Unit) {
        token?.let {
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

    fun getMe(callback: (UserData) -> Unit) {
        token?.let {
            val getMe = userService.getMe()
            getMe.enqueue(object: Callback<UserWrapper> {
                override fun onResponse(
                    call: Call<UserWrapper>,
                    response: Response<UserWrapper>
                ) {
                    if(response.isSuccessful){
                        response.body()?.users?.get(0)?.let { user ->
                            callback(user)
                        }
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
}