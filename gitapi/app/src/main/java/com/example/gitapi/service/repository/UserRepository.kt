package com.example.gitapi.service.repository

import android.app.DatePickerDialog
import android.content.Context
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.remote.RetrofitClient
import com.example.gitapi.service.repository.remote.UserService
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(UserService::class.java)

    private fun list(call: Call<List<UserModel>>, listener: ApiListener<List<UserModel>>){
        val response = call.enqueue(object : Callback<List<UserModel>>{
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.unexpected_error))
            }

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.code() != UserConstants.HTTP.SUCCESS) {
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

        })
    }

}