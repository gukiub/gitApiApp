package com.example.gitapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ValidationListener
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.UserRepository
import com.example.gitapi.service.repository.local.SecurityPreferences
import com.example.gitapi.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mUserRepository = UserRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)
    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    fun search(search: String){
        mUserRepository.list(search, object : ApiListener<UserModel>{
            override fun onSuccess(model: UserModel) {
                mValidation.value = ValidationListener()
                mSharedPreferences.store(UserConstants.SHARED.SEARCH_KEY, model.login)
                mSharedPreferences.store(UserConstants.SHARED.IMG_AVATAR, model.avatarUrl)

                if (model.name != null) {
                    mSharedPreferences.store(UserConstants.SHARED.NAME, model.name)
                } else {
                    mSharedPreferences.store(UserConstants.SHARED.NAME, model.login)
                }

                RetrofitClient.addHeader(model.login)

            }

            override fun onFailure(str: String){
                mValidation.value = ValidationListener(str)
            }
        })
    }


}