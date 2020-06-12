package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var mRepository = PersonRepository()

    private var mTextWelcome = MutableLiveData<String>().apply { value = "hello Wold" }
    var textWelcome = mTextWelcome

    private var mLogin = MutableLiveData<Boolean>()
    var login = mLogin

    init {
        mTextWelcome.value = "hello world!"
    }

    fun login(login: String){
        val ret = mRepository.login(login)
        mLogin.value = ret
    }

}