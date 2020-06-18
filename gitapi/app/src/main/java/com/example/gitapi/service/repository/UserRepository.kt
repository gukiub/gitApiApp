package com.example.gitapi.service.repository

import android.app.DatePickerDialog
import android.content.Context
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.model.RepoModel
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

class UserRepository(val context: Context): BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(UserService::class.java)

    fun list(search: String, listener: ApiListener<UserModel>){

        if (!isConnectionAvaliable(context)){
            listener.onFailure(context.getString(R.string.internet_required))
            return
        }

        val call: Call<UserModel> = mRemote.get(search)
        call.enqueue(object : Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.unexpected_error))
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                when {
                    response.code() == UserConstants.HTTP.SUCCESS -> {
                        response.body()?.let { listener.onSuccess(it) }
                    }
                    response.code() == UserConstants.HTTP.NOTFOUND -> {
                        val validation = context.getString(R.string.user_not_found)
                        listener.onFailure(validation)
                    }
                    response.code() == UserConstants.HTTP.FORBIDDEN -> {
                        val validation = context.getString(R.string.forbidden)
                        listener.onFailure(validation)
                    }
                    else -> {
                        val validation = context.getString(R.string.unexpected_error)
                        listener.onFailure(validation)
                    }
                }
            }

        })
    }

}