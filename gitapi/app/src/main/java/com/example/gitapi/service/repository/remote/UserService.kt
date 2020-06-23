package com.example.gitapi.service.repository.remote

import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("users/{user}")
    fun get(@Path(value = "user", encoded = true) user: String): Call<UserModel>

    @GET("users/{user}/repos?per_page=${RepoConstantants.SHARED.MAX_PER_PAGE}&")
    fun list(@Path("user", encoded = true) user: String,
             @Query("page", encoded = true) pages: Int): Call<ArrayList<RepoModel>>


}