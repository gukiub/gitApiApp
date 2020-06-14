package com.example.gitapi.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ValidationListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.repository.ReposRepository
import com.example.gitapi.service.repository.local.SecurityPreferences


class RepositoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = ReposRepository(application)

    private var mSharedPreferences = SecurityPreferences(application)

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

                mImage.value = mSharedPreferences.get(UserConstants.SHARED.IMG_AVATAR)
                mName.value = mSharedPreferences.get(UserConstants.SHARED.NAME)
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }

        }
        mRepository.list(listener)
    }


    fun limparSessao(){
        mSharedPreferences.remove(UserConstants.SHARED.SEARCH_KEY)
        mSharedPreferences.remove(UserConstants.SHARED.IMG_AVATAR)
        mSharedPreferences.remove(UserConstants.SHARED.NAME)
    }

}