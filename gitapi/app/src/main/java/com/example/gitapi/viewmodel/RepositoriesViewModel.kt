package com.example.gitapi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ValidationListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.ReposRepository
import com.example.gitapi.service.repository.UserRepository


class RepositoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = ReposRepository(application)
    private val mUserRepository = UserRepository(application)

    private val mList = MutableLiveData<List<RepoModel>>()
    var repos: LiveData<List<RepoModel>> = mList

    private val mImage = MutableLiveData<String>()
    var image: LiveData<String> = mImage

    private val mName = MutableLiveData<String>()
    var name: LiveData<String> = mName

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    fun list(){
       val listener = object : ApiListener<List<RepoModel>>{
            override fun onSuccess(model: List<RepoModel>) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }

        }
        mRepository.list(listener)
    }

    fun user(){
        object : ApiListener<UserModel>{
            override fun onSuccess(model: UserModel) {
                mImage.value = model.avatarUrl
                mName.value = model.name
            }

            override fun onFailure(str: String) {
                Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show()
            }
        }
    }


}