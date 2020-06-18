package com.example.gitapi.service.repository

import android.content.Context
import android.net.ConnectivityManager


open class BaseRepository(context: Context) {
    fun isConnectionAvaliable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}