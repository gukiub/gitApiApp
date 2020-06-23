package com.example.gitapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ValidationListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.ReposRepository
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

    private val mRepository = ReposRepository(application)

    private val mRepo = MutableLiveData<RepoModel>()
    var repo: LiveData<RepoModel> = mRepo

    private val mList = MutableLiveData<ArrayList<RepoModel>>()
    var list: LiveData<ArrayList<RepoModel>> = mList

    private val mDesc = MutableLiveData<String>()
    var desc: LiveData<String> = mDesc

    private val mImage = MutableLiveData<String>()
    var image: LiveData<String> = mImage

    private val mName = MutableLiveData<String>()
    var name: LiveData<String> = mName

    private var user = ""

    fun search(search: String){
        mUserRepository.list(search, object : ApiListener<UserModel>{
            override fun onSuccess(model: UserModel) {
                mValidation.value = ValidationListener()

                mName.value = model.name.let { "nothing to show" }

                mImage.value = model.avatarUrl
                mName.value = model.name

                RetrofitClient.addHeader(model.login)
            }

            override fun onFailure(str: String){
                mValidation.value = ValidationListener(str)
            }
        })
        this.user = search
    }


    fun list(page: Int){
        val listener = object : ApiListener<ArrayList<RepoModel>>{
            override fun onSuccess(model: ArrayList<RepoModel>) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }
        }
        mRepository.list(user ,listener, page)
    }





}