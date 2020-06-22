package com.example.gitapi.service.model


import java.io.Serializable

class RepoInfoModel(var id: Int,
                    var name: String,
                    var desc: String,
                    var stars: Int,
                    var forks: Int,
                    var issues: Int,
                    var url: String,
                    var date: String) : Serializable

