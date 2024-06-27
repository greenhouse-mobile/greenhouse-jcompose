package com.integradis.greenhouse.repositories

import android.util.Log
import com.integradis.greenhouse.model.data.crops.UserData
import com.integradis.greenhouse.model.data.crops.UserWrapper
import com.integradis.greenhouse.shared.SharedPreferencesHelper
import com.integradis.greenhouse.shared.data.remote.UserService
import com.integradis.greenhouse.shared.data.remote.UserServiceFactory

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class UserRepository(
    private val userService: UserService = UserServiceFactory.getUserService(),
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {

    private val token: String?
        get() = sharedPreferencesHelper.getToken()

//    fun getUser(username: String, callback: (List<UserData>) -> Unit) {
//        token?.let {
//            val getUser = userService.getUser(username)
//            getUser.enqueue(object: Callback<UserWrapper> {
//                override fun onResponse(
//                    call: Call<UserWrapper>,
//                    response: Response<UserWrapper>
//                ) {
//                    if(response.isSuccessful){
//                        callback(response.body()?.users ?: emptyList())
//                    }
//                }
//                override fun onFailure(
//                    call: Call<UserWrapper>,
//                    t: Throwable
//                ) {
//                    t.message?.let {
//                        Log.d("UserRepository", it)
//                    }
//                }
//            })
//        }
//    }

    fun getMe(callback: (UserData?) -> Unit) {
        token?.let {
            val getMe = userService.getMe("Bearer $it")
            getMe.enqueue(object: Callback<UserData> {
                override fun onResponse(
                    call: Call<UserData>,
                    response: Response<UserData>
                ) {
                    if(response.isSuccessful){
                        callback(response.body())
                    }
                }
                override fun onFailure(
                    call: Call<UserData>,
                    t: Throwable
                ) {
                    t.message?.let {message ->
                        Log.d("UserRepository", message)
                    }
                }
            })
        }
    }
}