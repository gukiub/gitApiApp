package com.example.gitapi.service.listener

interface ReposListener {

    fun onListClick(id: Int,
                    name: String,
                    desc: String,
                    stars: Int,
                    forks: Int,
                    issues: Int,
                    html: String,
                    date: String)

}