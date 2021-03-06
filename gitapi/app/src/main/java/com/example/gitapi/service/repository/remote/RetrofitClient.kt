package com.example.gitapi.service.repository.remote

import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.repository.local.SecurityPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object{
        private lateinit var retrofit: Retrofit
        private const val baseUrl = "https://api.github.com/"
        private var user = ""
        private var personalToken = "4487530c142f29639fc13d43395e0186c864ea21"

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor{ chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(RepoConstantants.SHARED.USER, user)
                    .addHeader(RepoConstantants.SHARED.TOKEN, personalToken)
                    .build()
                chain.proceed(request)
            }


            if (!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun addHeader(user: String){
            this.user = user
        }

        fun <S> createService(serviceClass: Class<S>): S{
            return getRetrofitInstance()
                .create(serviceClass)
        }

    }
}