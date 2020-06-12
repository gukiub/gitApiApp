package com.example.gitapi.service.repository.remote

import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users/{user}")
    fun get(@Path(value = "user", encoded = true) user: String): Call<UserModel>

    @GET("users/{user}/repos")
    fun list(@Path(value = "user", encoded = true) user: String): Call<List<RepoModel>>

}