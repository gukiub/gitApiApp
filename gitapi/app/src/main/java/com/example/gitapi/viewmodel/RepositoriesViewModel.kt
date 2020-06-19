package com.example.gitapi.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ValidationListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.repository.ReposRepository
import com.example.gitapi.service.repository.local.SecurityPreferences
import com.example.gitapi.view.adapter.PaginationScrollListener


class RepositoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = ReposRepository(application)

    private var mSharedPreferences = SecurityPreferences(application)

    private val mList = MutableLiveData<ArrayList<RepoModel>>()
        var repos: LiveData<ArrayList<RepoModel>> = mList

    private val mImage = MutableLiveData<String>()
    var image: LiveData<String> = mImage

    private val mName = MutableLiveData<String>()
    var name: LiveData<String> = mName

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation



    fun list(page: Int){
       val listener = object : ApiListener<ArrayList<RepoModel>>{
            override fun onSuccess(model: ArrayList<RepoModel>) {
                mList.value = model

                mImage.value = mSharedPreferences.get(UserConstants.SHARED.IMG_AVATAR)
                mName.value = mSharedPreferences.get(UserConstants.SHARED.NAME)
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }
        }
        mRepository.list(listener, page)
    }



}