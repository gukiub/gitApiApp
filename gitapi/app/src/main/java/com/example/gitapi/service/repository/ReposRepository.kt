package com.example.gitapi.service.repository

import android.content.Context
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.repository.local.SecurityPreferences
import com.example.gitapi.service.repository.remote.RetrofitClient
import com.example.gitapi.service.repository.remote.UserService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(UserService::class.java)
    private var mSharedPreferences = SecurityPreferences(context)

    fun list(listener: ApiListener<List<RepoModel>>){
        val call: Call<List<RepoModel>> = mRemote.list(mSharedPreferences.get(UserConstants.SHARED.SEARCH_KEY))
        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.unexpected_error))
            }

            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
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