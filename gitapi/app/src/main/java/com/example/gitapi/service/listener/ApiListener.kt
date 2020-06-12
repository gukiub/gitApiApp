package com.example.gitapi.service.listener

interface ApiListener<T> {

    fun onSuccess(model: T)

    fun onFailure(str: String)
}