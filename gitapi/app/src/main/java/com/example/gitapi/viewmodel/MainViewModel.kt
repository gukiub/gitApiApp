package com.example.gitapi.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepo = MutableLiveData<String>()
    var repo: LiveData<String> = mRepo

    fun search(search: String){
        mRepo.value = search
    }
}