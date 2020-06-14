package com.example.gitapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.service.repository.ReposRepository


class RepoInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = ReposRepository(application)

    private val mRepo = MutableLiveData<RepoModel>()
    var repo: LiveData<RepoModel> = mRepo

    private val mdesc = MutableLiveData<String>()
    var desc: LiveData<String> = mdesc

}