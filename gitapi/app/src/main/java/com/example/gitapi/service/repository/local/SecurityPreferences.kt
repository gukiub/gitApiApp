package com.example.gitapi.service.repository.local

import android.content.Context
import android.content.SharedPreferences
import com.example.gitapi.service.model.RepoModel

class SecurityPreferences(context: Context) {

    private val mPreferences: SharedPreferences = context.getSharedPreferences("infoShared", Context.MODE_PRIVATE)

    fun store(key: String, value: String){
        mPreferences.edit().putString(key, value).apply()
    }

    fun remove(key: String){
        mPreferences.edit().remove(key).apply()
    }

    fun get(key: String): String{
        return mPreferences.getString(key, "") ?: ""
    }

}