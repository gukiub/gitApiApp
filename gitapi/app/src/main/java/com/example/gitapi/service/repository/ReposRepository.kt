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

class ReposRepository(val context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(UserService::class.java)
    private var mSharedPreferences = SecurityPreferences(context)

    fun list(user: String, listener: ApiListener<ArrayList<RepoModel>>, page: Int) {

        if (!isConnectionAvaliable(context)) {
            listener.onFailure(context.getString(R.string.internet_required))
            return
        }

        val call: Call<ArrayList<RepoModel>> = mRemote.list(user, page)
        call.enqueue(object : Callback<ArrayList<RepoModel>> {
            override fun onFailure(call: Call<ArrayList<RepoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.unexpected_error))
            }

            override fun onResponse(
                call: Call<ArrayList<RepoModel>>,
                response: Response<ArrayList<RepoModel>>
            ) {
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